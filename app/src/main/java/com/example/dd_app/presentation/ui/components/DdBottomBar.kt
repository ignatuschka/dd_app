package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.navigation.BottomNavItems
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun DdBottomBar(
    items: List<BottomNavItems> = listOf(),
    currentRoute: String,
    onItemClick: (String) -> Unit
) {

    NavigationBar(
        modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
        containerColor = Colors.White,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentRoute,
                onClick = { onItemClick(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "RouteIcon",
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Colors.Purple,
                    selectedTextColor = Colors.Dark,
                    unselectedIconColor = Colors.Gray,
                    unselectedTextColor = Colors.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}