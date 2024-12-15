package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dd_app.presentation.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DdAppBar(
    modifier: Modifier = Modifier,
    popBack: (() -> Unit)? = null,
    title: String = "",
    actions: @Composable (RowScope.() -> Unit) = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    containerColor: Color = Colors.White
) {
    TopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor),
        title = {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                color = Colors.Dark,
            )
        },
        navigationIcon = {
            if (popBack != null) IconButton(onClick = popBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Colors.Purple
                )
            }
        },
        actions = actions
    )
}