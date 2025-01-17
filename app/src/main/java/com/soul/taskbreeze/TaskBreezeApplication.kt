package com.soul.taskbreeze

import android.app.Application
import com.soul.taskbreeze.core.ConnectivityChecker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TaskBreezeApplication : Application() {
    @Inject
    lateinit var connectivityChecker: ConnectivityChecker
}