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
import com.example.dd_app.core.convert.TimeDifferenceConvert
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.StartFinishTimeText
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.UserActivityDetailsViewModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserActivityDetailsScreen(
    popBack: () -> Unit = {},
    viewModel: UserActivityDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val now = LocalDateTime.now()
    val modifier = Modifier.padding(start = 32.dp, top = 24.dp, end = 32.dp)
    val subtitleModifier = Modifier.padding(horizontal = 32.dp)

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
                val userActivity = uiState.userActivity!!
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        modifier = modifier,
                        text = userActivity.userName ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Colors.Purple,
                    )
                    Text(
                        modifier = modifier,
                        text = if (userActivity.distanceMeters > 1000) "${userActivity.distanceMeters.toDouble() / 1000} км" else "${userActivity.distanceMeters} м",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Colors.Dark,
                    )
                    Text(
                        modifier = subtitleModifier,
                        text = TimeDifferenceConvert().timeAgo(
                            userActivity.exerciseStart,
                            now,
                            stringResource(R.string.ago),
                            stringResource(R.string.rightNow)
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Colors.Gray
                    )
                    Text(
                        modifier = modifier,
                        text = TimeDifferenceConvert().timeDifference(
                            userActivity.exerciseStart,
                            userActivity.exerciseEnd
                        ),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Colors.Dark,
                    )
                    StartFinishTimeText(
                        modifier = subtitleModifier,
                        startTime = DateConvert().format(userActivity.exerciseStart, "HH:mm"),
                        finishTime = DateConvert().format(userActivity.exerciseEnd, "HH:mm")
                    )
                    DdTextField(
                        modifier = modifier,
                        value = userActivity.comment,
                        enabled = false,
                        singleLine = false
                    )
                }
            } else Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.empty),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark
                )
            }
        }
    }
}