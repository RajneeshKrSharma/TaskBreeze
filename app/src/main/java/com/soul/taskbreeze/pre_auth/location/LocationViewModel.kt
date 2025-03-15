package com.soul.taskbreeze.pre_auth.location

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.abs
import kotlin.math.sqrt

class LocationViewmodel: ViewModel() {
    private val _distanceMovedInfo = mutableStateOf<String>("")
    val distanceMovedInfo: State<String> = _distanceMovedInfo

    fun initializeMovementDetector(context: Context) {
        MovementDetector(context) { isMoving, distance ->
            _distanceMovedInfo.value = if (isMoving) {
                "Moving: Distance moved = ${"%.2f".format(distance)} meters"
            } else {
                "Not moving"
            }
        }.apply {
            startTracking()
        }
    }
}

class MovementDetector(
    val context: Context,
    val onMovementChanged: (Boolean, Float) -> Unit // Includes distance
) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private var lastAcceleration: Float? = null  // Start as null to initialize properly
    private val movementThreshold = 0.05f // Adjusted for sensitivity
    private var totalDistance = 0f
    private var lastUpdateTime = 0L // Timestamp to debounce updates

    fun startTracking() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    fun stopTracking() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUpdateTime < 500) return // Debounce to avoid rapid updates
        lastUpdateTime = currentTime

        event?.let {
            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]

            val newAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            if (lastAcceleration == null) {
                lastAcceleration = newAcceleration // Initialize properly
                return
            }

            val delta = abs(newAcceleration - lastAcceleration!!) // Safe to use now

            val isMoving = delta > movementThreshold
            if (isMoving) {
                totalDistance += delta * 0.5f // Estimate movement (you can refine this)
            }

            onMovementChanged(isMoving, totalDistance)

            lastAcceleration = newAcceleration
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
