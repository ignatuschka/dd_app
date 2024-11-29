package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DdTextButton(onClick: () -> Unit = {}, text: String = "Preview") {
    TextButton(onClick = onClick) {
        Text(
            text = text, fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            lineHeight = 24.sp,
            color = Color(75, 9, 243),
        )
    }
}