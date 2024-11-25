package com.example.dd_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dd_app.presentation.ui.screens.LoginScreen
import com.example.dd_app.presentation.ui.screens.RegistrationScreen
import com.example.dd_app.presentation.ui.screens.WelcomeScreen

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Registration : Screen("registration")
    data object Login : Screen("login")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                navToRegistration = { navController.navigate(Screen.Registration.route) },
                navToLogin = { navController.navigate(Screen.Login.route) })
        }
        composable(Screen.Registration.route) { RegistrationScreen(popBack = { navController.popBackStack() }) }
        composable(Screen.Login.route) { LoginScreen(popBack = { navController.popBackStack() }) }
    }
}