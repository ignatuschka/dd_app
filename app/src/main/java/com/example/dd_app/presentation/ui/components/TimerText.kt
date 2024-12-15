package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.dd_app.presentation.ui.theme.Colors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerText(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var timer by remember { mutableLongStateOf(0) }

    LaunchedEffect(timer) {
        scope.launch {
            delay(1000)
            timer++
        }
    }

    Text(
        modifier = modifier,
        text = formatTime(timer),
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
        color = Colors.Dark
    )
}

private fun formatTime(timeInSeconds: Long): String {
    val hours = timeInSeconds / 3600
    val minutes = (timeInSeconds % 3600) / 60
    val seconds = timeInSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}