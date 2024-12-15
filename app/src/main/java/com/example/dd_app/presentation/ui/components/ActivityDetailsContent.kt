package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun ActivityDetailsContent(
    scrollState: ScrollState,
    userName: String?,
    distanceMeters: String,
    exerciseDate: String,
    exerciseDuration: String,
    startTime: String,
    finishTime: String,
    textField: @Composable (modifier: Modifier) -> Unit
) {
    val modifier = Modifier.padding(start = 32.dp, top = 24.dp, end = 32.dp)
    val subtitleModifier = Modifier.padding(horizontal = 32.dp)
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        if (userName != null) Text(
            modifier = modifier,
            text = userName,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Purple,
        )
        Text(
            modifier = modifier,
            text = distanceMeters,
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark,
        )
        Text(
            modifier = subtitleModifier,
            text = exerciseDate,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Gray
        )
        Text(
            modifier = modifier,
            text = exerciseDuration,
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark,
        )
        StartFinishTimeText(
            modifier = subtitleModifier,
            startTime = startTime,
            finishTime = finishTime
        )
        textField(modifier)
    }
}