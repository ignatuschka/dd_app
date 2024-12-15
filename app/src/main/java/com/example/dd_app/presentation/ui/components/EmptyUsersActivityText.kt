package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun EmptyUsersActivityText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.empty),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.waitForUserActivity),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Colors.Gray
        )
    }
}