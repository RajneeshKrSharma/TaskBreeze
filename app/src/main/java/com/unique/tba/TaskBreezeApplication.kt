package com.unique.tba

import android.app.Application
import com.unique.tba.core.ConnectivityChecker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TaskBreezeApplication : Application() {
    @Inject
    lateinit var connectivityChecker: ConnectivityChecker
}