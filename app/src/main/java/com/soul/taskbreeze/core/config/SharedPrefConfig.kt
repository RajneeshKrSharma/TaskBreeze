package com.soul.taskbreeze.core.config

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefConfig @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun isUserViaOtp() = true
}
