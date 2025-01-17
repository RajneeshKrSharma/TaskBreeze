package com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation

sealed class Screen(val route: String) {
    data object AppTourScreen: Screen("app_tour_screen")
    data object LoginScreen: Screen("login_screen")
    data object HomeScreen: Screen("home_screen")
    data object PreAuthScreen: Screen("pre_auth")
    data object SplashScreen: Screen("splash")
}