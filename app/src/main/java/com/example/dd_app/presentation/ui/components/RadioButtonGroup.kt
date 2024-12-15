package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.ui.theme.Colors

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
                text = "$el",
                style = MaterialTheme.typography.bodyLarge,
                color = Colors.Dark,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}