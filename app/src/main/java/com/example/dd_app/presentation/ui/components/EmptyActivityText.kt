package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun EmptyActivityText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.emptyMyActivitiesTitle), textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.emptyMyActivitiesSubtitle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Gray
        )
    }
}