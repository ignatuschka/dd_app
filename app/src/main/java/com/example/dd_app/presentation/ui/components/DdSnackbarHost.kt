package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun DdSnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = Colors.White,
            contentColor = Colors.Dark,
            actionColor = Colors.Purple,
        )
    }
}