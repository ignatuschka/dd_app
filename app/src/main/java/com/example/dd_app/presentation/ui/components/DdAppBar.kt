package com.example.dd_app.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DdAppBar(popBack: () -> Unit, title: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(255, 255, 255)),
        title = {
            Text(
                title, fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                lineHeight = 24.sp,
                color = Color(16, 16, 16),
            )
        },
        navigationIcon = {
            IconButton(onClick = popBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color(75, 9, 243)
                )
            }
        }
    )
}