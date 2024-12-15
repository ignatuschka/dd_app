package com.example.dd_app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sports
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dd_app.presentation.ui.screens.ActivityPage
import com.example.dd_app.presentation.ui.screens.ChangePasswordScreen
import com.example.dd_app.presentation.ui.screens.MyActivityDetailsScreen
import com.example.dd_app.presentation.ui.screens.ProfilePage
import com.example.dd_app.presentation.ui.screens.UserActivityDetailsScreen

sealed class BottomNavItems(val route: String, val icon: ImageVector, val label: String) {
    data object Activity : BottomNavItems("activity", Icons.Filled.Sports, "Активность")
    data object Profile : BottomNavItems("profile", Icons.Filled.Person, "Профиль")
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    logout: () -> Unit
) {

    NavHost(navController, startDestination = BottomNavItems.Activity.route, modifier = modifier) {
        composable(BottomNavItems.Activity.route) {
            ActivityPage(
                navToMyActivity = { id ->
                    navController.navigate(
                        Screen.MyActivityDetails(activityId = id).route
                    )
                },
                navToUserActivity = { id ->
                    navController.navigate(
                        Screen.UserActivityDetails(activityId = id).route
                    )
                })
        }
        composable(BottomNavItems.Profile.route) {
            ProfilePage(logout = logout, navToChangePasswordScreen = {
                navController.navigate(Screen.ChangePassword.route)
            })
        }
        composable(Screen.ChangePassword.route) { ChangePasswordScreen(popBack = { navController.popBackStack() }) }
        composable(
            route = Screen.UserActivityDetails.routeWithArg,
            arguments = listOf(navArgument(Screen.UserActivityDetails.argActivityId) {
                type = NavType.IntType
            })
        ) {
            UserActivityDetailsScreen(popBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.MyActivityDetails.routeWithArg,
            arguments = listOf(navArgument(Screen.MyActivityDetails.argActivityId) {
                type = NavType.IntType
            })
        ) { MyActivityDetailsScreen(popBack = { navController.popBackStack() }) }
    }
}