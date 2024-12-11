package com.example.dd_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dd_app.presentation.ui.screens.LoginScreen
import com.example.dd_app.presentation.ui.screens.MainScreen
import com.example.dd_app.presentation.ui.screens.NewMyActivityScreen
import com.example.dd_app.presentation.ui.screens.RegistrationScreen
import com.example.dd_app.presentation.ui.screens.WelcomeScreen
import com.example.dd_app.presentation.viewmodel.AuthViewModel

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Registration : Screen("registration")
    data object Login : Screen("login")
    data object Main : Screen("main")
    data object ChangePassword : Screen("changePassword")
    data class UserActivityDetails(val activityId: Int) :
        Screen("userActivityDetails/$activityId") {
        companion object {
            const val routeWithArg = "userActivityDetails/{activityId}"
            const val argActivityId = "activityId"
        }
    }

    data class MyActivityDetails(val activityId: Int) : Screen("myActivityDetails/$activityId") {
        companion object {
            const val routeWithArg = "myActivityDetails/{activityId}"
            const val argActivityId = "activityId"
        }
    }

    data object NewMyActivity : Screen("newMyActivity")
}

@Composable
fun AppNavHost(viewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController,
        startDestination = if (uiState) Screen.Main.route else Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                navToRegistration = { navController.navigate(Screen.Registration.route) },
                navToLogin = { navController.navigate(Screen.Login.route) })
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                popBack = { navController.popBackStack() },
                navToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screen.Login.route) {
            LoginScreen(
                popBack = { navController.popBackStack() },
                navToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screen.Main.route) {
            MainScreen(
                logout = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Main.route) {
                            inclusive = true
                        }
                    }
                },
                navToNewMyActivity = { navController.navigate(Screen.NewMyActivity.route) }
            )
        }
        composable(Screen.NewMyActivity.route) {
            NewMyActivityScreen(popBack = { navController.popBackStack() })
        }
    }
}