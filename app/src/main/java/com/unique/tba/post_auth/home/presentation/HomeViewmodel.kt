package com.unique.tba.post_auth.home.presentation

import androidx.lifecycle.ViewModel
import com.unique.tba.core.config.SharedPrefConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val sharedPrefConfig: SharedPrefConfig
): ViewModel() {
    fun logout() {
        sharedPrefConfig.clearAll()
    }
}