package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun myActivityDetailsAppbarActions(
    onDelete: () -> Unit,
    onShare: () -> Unit
): @Composable (RowScope.() -> Unit) =
    {
        IconButton(onDelete) {
            Icon(Icons.Filled.Delete, "delete", tint = Colors.Purple)
        }
        IconButton(onShare) {
            Icon(Icons.Filled.Share, "share", tint = Colors.Purple)
        }
    }
