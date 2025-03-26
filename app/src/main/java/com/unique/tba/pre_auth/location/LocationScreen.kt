package com.unique.tba.pre_auth.location


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationRequest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.unique.tba.core.presentation.BaseCompose
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

enum class LocationUiFeatures {
    Location, Speedometer
}

@Composable
fun LocationScreen(
    navController: NavController,
    viewmodel: LocationViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var eta by remember { mutableIntStateOf(6) }
    var locationTime by remember { mutableLongStateOf(0L) }
    var locationDetails by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    val coroutineScope = rememberCoroutineScope()
    var hasPermission by remember { mutableStateOf(false) }
    var speed = remember { mutableStateOf(0f) }

    // State to track the selected bottom bar item
    var selectedItem by remember { mutableStateOf(LocationUiFeatures.Location) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
        if (granted) {
            fetchLocation(context, fusedLocationClient) { details, time, movingSpeed ->
                locationDetails = details
                locationTime = time.toLong()
                Log.d("Rajneesh", "onLocationResult: movingSpeed.toFloat() - ${movingSpeed.toFloat()}")
                speed.value = movingSpeed.toFloat()
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(1000)
                eta = if (eta > 0) eta - 1 else 6
            }
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            hasPermission = true
            fetchLocation(context, fusedLocationClient) { details, time, movingSpeed ->
                Log.d("Rajneesh", "onLocationResult: movingSpeed.toFloat() checkSelfPermission --- ${movingSpeed.toFloat()}")

                locationDetails = details
                locationTime = time.toLong()
                speed.value = movingSpeed.toFloat()
            }
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    viewmodel.initializeMovementDetector(context)

    BaseCompose(
        topBar = null,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(
                    icon = Icons.Filled.LocationOn,
                    label = "Location",
                    isSelected = selectedItem == LocationUiFeatures.Location,
                    onClick = { selectedItem = LocationUiFeatures.Location }
                )
                BottomBarItem(
                    icon = Icons.Filled.Refresh,
                    label = "Speedometer",
                    isSelected = selectedItem == LocationUiFeatures.Speedometer,
                    onClick = { selectedItem = LocationUiFeatures.Speedometer }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD8E0E2))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            when (selectedItem) {
                LocationUiFeatures.Location -> LocationDetailsTable(locationDetails, viewmodel)
                LocationUiFeatures.Speedometer -> SpeedometerScreen(speedFromLocation = speed.value)
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun BottomBarItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color.Blue else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isSelected) Color.Blue else Color.Black,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}


@Composable
fun HeaderSection(eta: Int, locationTime: Long) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Refreshing location . . ( ETA : $eta sec/s )", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun LocationDetailsTable(locationDetails: Map<String, String>, viewmodel: LocationViewmodel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Location Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Device moving info : ${viewmodel.distanceMovedInfo.value}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        locationDetails.entries.forEachIndexed { index, (label, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (index % 2 == 0) Color.DarkGray.copy(alpha = 0.2f) else Color.DarkGray.copy(
                            0.1f
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(vertical = 4.dp, horizontal = 4.dp),
                    text = label,
                    fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                Text(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(vertical = 4.dp, horizontal = 4.dp),
                    text = value,
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@SuppressLint("MissingPermission")
private fun fetchLocation(context: Context, fusedLocationClient: FusedLocationProviderClient, onLocationFetched: (Map<String, String>, String, Double) -> Unit) {
    val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, // Priority
        5000 // Interval in milliseconds
    ).apply {
        setMinUpdateIntervalMillis(2000) // Fastest interval
    }.build()

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation ?: return

            // Calculate distance only if last location exists
            val prefs = context.getSharedPreferences("LocationPrefs", Context.MODE_PRIVATE)
            var lastLatitude = prefs.getFloat("lastLatitude", 0f).toDouble()
            var lastLongitude = prefs.getFloat("lastLongitude", 0f).toDouble()
            var totalDistance = prefs.getFloat("totalDistance", 0f).toDouble()

            val currentLat = location.latitude
            val currentLon = location.longitude

            try {

                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                val address = addresses?.firstOrNull()

                val speed = location.speed * 3.6

                //val speed = Random.nextInt(0, 121).toDouble()
                Log.d("Rajneesh", "onLocationResult: speed - $speed")

                if (speed > 0.5) { // Only update if moving
                    if (lastLatitude != 0.0 && lastLongitude != 0.0) {
                        val distance = calculateDistance(lastLatitude, lastLongitude, currentLat, currentLon)
                        totalDistance += distance
                    }

                    // Save new values to SharedPreferences
                    prefs.edit().apply {
                        putFloat("lastLatitude", currentLat.toFloat())
                        putFloat("lastLongitude", currentLon.toFloat())
                        putFloat("totalDistance", totalDistance.toFloat())
                        apply()
                    }
                }

                val locationDetails = mapOf(
                    "Distance Traveled" to "${"%.2f".format(totalDistance)} km",
                    "Longitude" to location.longitude.toString(),
                    "Latitude" to location.latitude.toString(),
                    "Address" to (address?.getAddressLine(0) ?: "N/A"),
                    "Locality" to (address?.locality ?: "N/A"),
                    "Sub Locality" to (address?.subLocality ?: "N/A"),
                    "Thoroughfare" to (address?.thoroughfare ?: "N/A"),
                    "Feature Name" to (address?.featureName ?: "N/A"),
                    "Premises" to (address?.premises ?: "N/A"),
                    "Postal Code" to (address?.postalCode ?: "N/A"),
                    "Admin Area" to (address?.adminArea ?: "N/A"),
                    "Sub-Admin Area" to (address?.subAdminArea ?: "N/A"),
                    "Country" to (address?.countryName ?: "N/A"),
                    "Country Code" to (address?.countryCode ?: "N/A"),
                    "Locale" to (address?.locale?.toString() ?: "N/A"),
                    "Max Address Lines" to (address?.maxAddressLineIndex?.toString() ?: "N/A"),
                    "Phone" to (address?.phone ?: "N/A"),
                    "Premises" to (address?.premises ?: "N/A"),
                    "Sub ThroughFare" to (address?.subThoroughfare ?: "N/A"),
                    "Url" to (address?.url ?: "N/A"),
                )
                val time = System.currentTimeMillis().toString()
                onLocationFetched(locationDetails, time, speed)
            } catch (_: Exception) { }
        }
    }
    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
}

@Composable
fun SpeedometerScreen(speedFromLocation: Float) {
    val animatedSpeed by animateFloatAsState(targetValue = speedFromLocation, animationSpec = tween(durationMillis = 1000), label = "Speed Animation")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Speedometer",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Speed: ${speedFromLocation.toInt()} km/hr",
            fontSize = 18.sp,
            color = getSpeedColor(speedFromLocation)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Speedometer(animatedSpeed)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Speedometer(speed: Float) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height - 40.dp.toPx()
        val radius = size.width / 2.2f

        // Speed range arc
        drawArc(Color.Green, 180f, 90f, false, size = Size(radius * 2, radius * 2), topLeft = Offset(centerX - radius, centerY - radius), style = Stroke(4.dp.toPx()))
        drawArc(Color.Yellow, 270f, 60f, false, size = Size(radius * 2, radius * 2), topLeft = Offset(centerX - radius, centerY - radius), style = Stroke(4.dp.toPx()))
        drawArc(Color.Red, 330f, 30f, false, size = Size(radius * 2, radius * 2), topLeft = Offset(centerX - radius, centerY - radius), style = Stroke(4.dp.toPx()))

        // Speed markings with values
        for (i in 0..12) {

            val angle = (180 + i * 15) * (PI / 180).toFloat()
            val markStart = Offset(centerX + (radius - 10.dp.toPx()) * cos(angle), centerY + (radius - 10.dp.toPx()) * sin(angle))
            val markEnd = Offset(centerX + (radius + 10.dp.toPx()) * cos(angle), centerY + (radius + 10.dp.toPx()) * sin(angle))
            drawLine(Color.White, start = markStart, end = markEnd, strokeWidth = 1.dp.toPx())

            val textOffsetX = centerX + (radius + 15.dp.toPx()) * cos(angle)
            val textOffsetY = centerY + (radius + 15.dp.toPx()) * sin(angle)
            drawContext.canvas.nativeCanvas.drawText(
                "${i * 10}",
                textOffsetX,
                textOffsetY,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.WHITE
                    textSize = 24f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }

        // Speed needle with animation
        val angle = (180 + (speed / 120) * 180) * (PI / 180).toFloat()
        val needleLength = radius - 20.dp.toPx()
        val needleX = centerX + needleLength * cos(angle)
        val needleY = centerY + needleLength * sin(angle)
        drawLine(Color.Red, start = Offset(centerX, centerY), end = Offset(needleX, needleY), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
    }
}


fun getSpeedColor(speed: Float): Color {
    return when {
        speed < 60 -> Color.Green
        speed < 100 -> Color.Yellow
        else -> Color.Red
    }
}

private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371.0 // Radius of Earth in km
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c // Distance in km
}