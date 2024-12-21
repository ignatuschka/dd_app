package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.dd_app.presentation.ui.theme.Colors

@Preview
@Composable
fun DdTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Preview",
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Colors.Purple,
        )
    }
}