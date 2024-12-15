package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun EmptyTextBox(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.empty),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark
        )
    }
}