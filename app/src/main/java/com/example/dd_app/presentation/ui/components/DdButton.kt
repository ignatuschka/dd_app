package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.ui.theme.Colors

@Preview
@Composable
fun DdButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Preview",
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    color: Color = Colors.Purple
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        contentPadding = contentPadding,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Colors.White,
        )
    }
}