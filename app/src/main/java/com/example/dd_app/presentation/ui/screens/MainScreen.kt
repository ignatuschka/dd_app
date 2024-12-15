package com.example.dd_app.presentation.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dd_app.R
import com.example.dd_app.presentation.navigation.BottomNavItems
import com.example.dd_app.presentation.navigation.MainNavHost
import com.example.dd_app.presentation.ui.components.DdBottomBar
import com.example.dd_app.presentation.ui.components.DdFloatingActionButton
import com.example.dd_app.presentation.ui.components.DdSnackbarHost
import com.example.dd_app.presentation.ui.theme.Colors
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(logout: () -> Unit, navToNewMyActivity: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val permissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    ) { result ->
        if (result) {
            requestLocationService(context, navToNewMyActivity)
        } else scope.launch {
            showSnackbar(context, snackbarHostState)
        }
    }

    Scaffold(
        containerColor = Colors.White,
        snackbarHost = { DdSnackbarHost(snackbarHostState) },
        bottomBar = {
            DdBottomBar(
                items = listOf(BottomNavItems.Activity, BottomNavItems.Profile),
                currentRoute = currentRoute ?: "",
                onItemClick = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        },
        floatingActionButton = {
            if (currentRoute == BottomNavItems.Activity.route) DdFloatingActionButton(
                onClick = {
                    permissionState.launchPermissionRequest()
                }
            )
        },
    ) { padding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(padding),
            logout = logout
        )
    }
}

private suspend fun showSnackbar(context: Context, snackbarHostState: SnackbarHostState) {
    snackbarHostState.currentSnackbarData?.dismiss()
    val snackResult = snackbarHostState.showSnackbar(
        context.getString(R.string.needLocationPermission),
        context.getString(R.string.settings),
        duration = SnackbarDuration.Short
    )
    if (snackResult == SnackbarResult.ActionPerformed) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
}

private fun requestLocationService(context: Context, onSuccess: () -> Unit) {
    val client = LocationServices.getSettingsClient(context)
    val locationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).build()
    val settingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .build()
    val task: Task<LocationSettingsResponse> = client.checkLocationSettings(settingsRequest)
    task.addOnSuccessListener { onSuccess() }
    task.addOnFailureListener { exception ->
        if (exception is ApiException) {
            if (exception.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                try {
                    val resolvable = exception as ResolvableApiException
                    resolvable.startResolutionForResult(context as Activity, 1000)
                } catch (_: IntentSender.SendIntentException) {
                }
            }
        }
    }
}