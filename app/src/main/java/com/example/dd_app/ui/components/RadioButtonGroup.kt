package com.example.dd_app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> RadioButtonGroup(selectedOption: T, onClick: (T) -> Unit, radioOptions: List<T>) {
    radioOptions.forEach { el ->
        Row(
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .selectable(
                    selected = (el == selectedOption),
                    onClick = { onClick(el) },
                    role = Role.RadioButton
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (el == selectedOption),
                onClick = null,
                colors = RadioButtonDefaults.colors(Color(75, 9, 243))
            )
            Text(
                text = "$el", fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Default,
                lineHeight = 24.sp,
                color = Color(16, 16, 16),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}