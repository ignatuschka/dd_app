package com.example.dd_app.presentation.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.dd_app.core.convert.TimeDifferenceConvert
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.StartFinishTimeText
import com.example.dd_app.presentation.ui.theme.Colors
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
    val modifier = Modifier.padding(start = 32.dp, top = 24.dp, end = 32.dp)
    val subtitleModifier = Modifier.padding(horizontal = 32.dp)
    val context = LocalContext.current
    val shareTitle = stringResource(R.string.share)
    val scroll = rememberScrollState()

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
            actions = {
                IconButton(onClick = {
                    viewModel.viewModelScope.launch {
                        val isDeleted = viewModel.deleteMyActivity()
                        if (isDeleted) popBack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "delete",
                        tint = Colors.Purple
                    )
                }
                IconButton(onClick = {
                    val shareText = viewModel.generateShareText()
                    if (shareText != null) {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(intent, shareTitle))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "share",
                        tint = Colors.Purple
                    )
                }
            }
        )
        uiState.myActivity?.let{
            Column(
                modifier = Modifier.verticalScroll(scroll)
            ) {
                Text(
                    modifier = modifier,
                    text = if (it.distanceMeters > 1000) "${it.distanceMeters.toDouble() / 1000} км" else "${it.distanceMeters} м",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark,
                )
                Text(
                    modifier = subtitleModifier,
                    text = TimeDifferenceConvert().timeAgo(
                        it.exerciseStart,
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
                        it.exerciseStart,
                        it.exerciseEnd
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark,
                )
                StartFinishTimeText(
                    modifier = subtitleModifier,
                    startTime = DateConvert().format(it.exerciseStart, "HH:mm"),
                    finishTime = DateConvert().format(it.exerciseEnd, "HH:mm")
                )
                DdTextField(
                    modifier = modifier,
                    value = uiState.comment,
                    onValueChange = { viewModel.onChangeComment(it) },
                    label = stringResource(R.string.comment),
                    singleLine = false
                )
            }
        }
    }
}