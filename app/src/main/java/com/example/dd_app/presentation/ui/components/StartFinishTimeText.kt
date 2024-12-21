package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun StartFinishTimeText(modifier: Modifier = Modifier, startTime: String, finishTime: String) {
    val startEndTimeModifier = Modifier.padding(horizontal = 8.dp)
    Row(modifier = modifier) {
        Text(
            text = stringResource(R.string.start),
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Dark
        )
        Text(
            modifier = startEndTimeModifier,
            text = startTime,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Gray
        )
        VerticalDivider(color = Colors.Gray, modifier = startEndTimeModifier.height(16.dp).align(Alignment.CenterVertically))
        Text(
            modifier = startEndTimeModifier,
            text = stringResource(R.string.finish),
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Dark
        )
        Text(
            text = finishTime,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Gray
        )
    }
}