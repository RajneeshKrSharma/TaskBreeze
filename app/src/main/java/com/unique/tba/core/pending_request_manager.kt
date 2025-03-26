package com.unique.tba.core

import android.util.Log
import com.unique.tba.pre_auth.pre_auth_loading.domain.use_case.GetPreAuthDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// Generic interface for API requests
interface ApiUseCase<T, in U> {  // T is the return type, U is the argument type
    suspend fun execute(args: U? = null): T
}

// Data model to hold the request and retry status
data class KModel<T, U>(
    val isAutoRetry: Boolean,
    val request: ApiUseCase<T, U>
)

// Manager for handling pending requests
@Singleton
class PendingRequestManager @Inject constructor(
    private var connectivityChecker: ConnectivityChecker
) {

    private val requestPool: MutableList<KModel<*, *>> = mutableListOf()

    init {
        Log.d("Rajneesh", "Initialize with some example requests (for demonstration)")
        CoroutineScope(Dispatchers.IO).launch {
            connectivityChecker.isConnected.collect { isConnected ->
                if (isConnected && requestPool.isNotEmpty()) {
                    executeRequests()
                }
            }
        }
    }

    // Method to add a request to the pool
    fun <T, U> addRequest(isAutoRetry: Boolean, request: ApiUseCase<T, U>, args: U? = null) {
        requestPool.add(KModel(isAutoRetry, request))
    }

    // Method to execute all requests in the pool
    private suspend fun executeRequests() {
        Log.d("Rajneesh", "executeRequests: ")
        val snapshot = requestPool.toList() // Create a snapshot of the collection
        snapshot.forEach { model ->
            try {
                // Use reflection to call execute() dynamically with args
                val result = model.request.execute()
                Log.d("Rajneesh", "executeRequests: ${model.request} : Request executed successfully with result: $result")
            } catch (e: Exception) {
                Log.e("Rajneesh", "Request execution failed: ${e.message}")
                // Handle retry logic here if needed
            }
        }
    }
}
