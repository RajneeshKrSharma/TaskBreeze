package com.unique.tba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unique.tba.auth.login.presentation.LoginScreen
import com.unique.tba.post_auth.split_expense.presentation.SplitExpenseScreen
import com.unique.tba.post_auth.home.presentation.HomeScreen
import com.unique.tba.pre_auth.location.LocationScreen
import com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthScreen
import com.unique.tba.pre_auth.presentation.Screen
import com.unique.tba.pre_auth.splash.presentation.SplashScreen
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
                        LoginScreen(navController = navController, context = this@MainActivity)
                    }
                    composable(route = Screen.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(route = Screen.SplitExpenseScreen.route) {
                        SplitExpenseScreen(navController = navController)
                    }
                    composable(route = Screen.LocationScreen.route) {
                        LocationScreen(navController = navController)
                    }
                }
            }
        }
    }
}