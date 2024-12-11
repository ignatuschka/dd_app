package com.example.dd_app.presentation.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dd_app.presentation.ui.components.EmptyUsersActivityText
import com.example.dd_app.presentation.ui.components.ListActivity
import com.example.dd_app.presentation.viewmodel.UsersActivitiesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersActivityTab(viewModel: UsersActivitiesViewModel = hiltViewModel(), navToUserActivity: (Int) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = { viewModel.updateUserActivities() }) {
        if (uiState.items.isEmpty())
            EmptyUsersActivityText()
        else
            ListActivity(uiState.items, true, navToUserActivity)
    }
}