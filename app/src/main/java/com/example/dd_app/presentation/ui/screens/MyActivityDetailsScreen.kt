package com.example.dd_app.presentation.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.convert.DistanceConvert
import com.example.dd_app.core.convert.timeAgo
import com.example.dd_app.core.convert.timeDifference
import com.example.dd_app.presentation.ui.components.ActivityDetailsContent
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.myActivityDetailsAppbarActions
import com.example.dd_app.presentation.viewmodel.MyActivityDetailsViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun MyActivityDetailsScreen(
    popBack: () -> Unit,
    viewModel: MyActivityDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val now = LocalDateTime.now()
    val context = LocalContext.current
    val shareTitle = stringResource(R.string.share)
    val scrollState = rememberScrollState()

    BackHandler {
        viewModel.viewModelScope.launch {
            viewModel.updateComment()
            popBack()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        DdAppBar(
            windowInsets = WindowInsets(0.dp),
            popBack = {
                viewModel.viewModelScope.launch {
                    viewModel.updateComment()
                    popBack()
                }
            },
            title = uiState.myActivity?.exerciseType?.title ?: "",
            actions = myActivityDetailsAppbarActions(onDelete = {
                viewModel.viewModelScope.launch {
                    val isDeleted = viewModel.deleteMyActivity()
                    if (isDeleted) popBack()

                }
            }, onShare = {
                val shareText = viewModel.generateShareText()
                if (shareText != null) {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    context.startActivity(Intent.createChooser(intent, shareTitle))
                }
            }),
        )
        uiState.myActivity?.let {
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
                        value = uiState.comment,
                        onValueChange = { viewModel.onChangeComment(it) },
                        label = stringResource(R.string.comment),
                        singleLine = false
                    )
                }
            )
        }
    }
}