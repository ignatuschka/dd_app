package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dd_app.R
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.convert.timeAgo
import com.example.dd_app.core.convert.timeDifference
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.StartFinishTimeText
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.UserActivityDetailsViewModel
import com.example.dd_app.core.convert.DistanceConvert
import com.example.dd_app.presentation.ui.components.ActivityDetailsContent
import com.example.dd_app.presentation.ui.components.EmptyTextBox
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserActivityDetailsScreen(
    popBack: () -> Unit = {},
    viewModel: UserActivityDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val now = LocalDateTime.now()
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DdAppBar(
            windowInsets = WindowInsets(0.dp),
            popBack = popBack,
            title = uiState.userActivity?.exerciseType?.title ?: ""
        )
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.getUserActivity() }) {
            if (uiState.userActivity != null) {
                uiState.userActivity?.let {
                    ActivityDetailsContent(
                        scrollState = scrollState,
                        userName = it.userName,
                        distanceMeters = DistanceConvert.toShortMeters(it.distanceMeters),
                        exerciseDate = it.exerciseStart.timeAgo(
                            now,
                            stringResource(R.string.ago),
                            stringResource(R.string.rightNow)
                        ),
                        exerciseDuration = it.exerciseStart.timeDifference(it.exerciseEnd),
                        startTime = DateConvert.format(it.exerciseStart, "HH:mm"),
                        finishTime = DateConvert.format(it.exerciseEnd, "HH:mm"),
                        textField = { _ ->
                            DdTextField(
                                value = it.comment,
                                enabled = false,
                                singleLine = false
                            )
                        }
                    )
                }
            } else EmptyTextBox(scrollState = scrollState)
        }
    }
}