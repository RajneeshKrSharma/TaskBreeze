package com.soul.taskbreeze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soul.taskbreeze.auth.login.presentation.LoginScreen
import com.soul.taskbreeze.post_auth.split_expense.presentation.SplitExpenseScreen
import com.soul.taskbreeze.post_auth.home.presentation.HomeScreen
import com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation.PreAuthScreen
import com.soul.taskbreeze.pre_auth.presentation.Screen
import com.soul.taskbreeze.pre_auth.splash.presentation.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(route = Screen.SplashScreen.route) {
                        SplashScreen(navController = navController)
                    }
                    composable(route = Screen.AppTourScreen.route) {
                        SplashScreen(navController = navController)
                    }
                    composable(route = Screen.PreAuthScreen.route) {
                        PreAuthScreen(navController = navController)
                    }
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }
                    composable(route = Screen.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(route = Screen.SplitExpenseScreen.route) {
                        SplitExpenseScreen(navController = navController)
                    }
                }
            }
        }
    }
}