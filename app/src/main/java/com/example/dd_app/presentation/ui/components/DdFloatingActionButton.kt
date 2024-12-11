package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun DdFloatingActionButton(onClick: () -> Unit = {}) {
    FloatingActionButton(
        modifier = Modifier.size(64.dp, 64.dp),
        shape = CircleShape,
        containerColor = Colors.Purple,
        contentColor = Colors.White,
        onClick = onClick,
    ) {
        Icon(Icons.Rounded.PlayArrow, "Floating action button.")
    }
}