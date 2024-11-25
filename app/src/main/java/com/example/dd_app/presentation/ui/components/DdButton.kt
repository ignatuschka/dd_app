package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DdButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Preview",
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(75, 9, 243)),
        contentPadding = contentPadding,
    ) {
        Text(
            text = text, fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            color = Color(255, 255, 255),
        )
    }
}