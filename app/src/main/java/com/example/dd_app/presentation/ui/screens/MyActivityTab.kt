package com.example.dd_app.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dd_app.presentation.ui.components.*
import com.example.dd_app.presentation.viewmodel.MyActivitiesViewModel

@Composable
fun MyActivityTab(viewModel: MyActivitiesViewModel = hiltViewModel(), navToMyActivity: (Int) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isEmpty())
        EmptyActivityText()
    else
        ListActivity(uiState, navToActivity = navToMyActivity)
}