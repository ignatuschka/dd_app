package com.example.dd_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dd_app.R
import com.example.dd_app.ui.components.DdButton
import com.example.dd_app.ui.components.DdTextButton

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Scaffold(
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier.size(379.dp, 335.dp),
                    painter = painterResource(id = R.drawable.welcome_screen_image),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(PaddingValues(0.dp, 32.dp, 0.dp, 16.dp)),
                    text = stringResource(R.string.welcomeScreenTitle),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    lineHeight = 35.sp,
                    color = Color(16, 16, 16),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.welcomeScreenSubtitle),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default,
                    lineHeight = 24.sp,
                    color = Color(128, 128, 128)
                )
                DdButton(
                    onClick = { navController.navigate("registration") },
                    text = stringResource(R.string.signUp),
                    modifier = Modifier.padding(PaddingValues(0.dp, 32.dp, 0.dp, 16.dp)),
                    contentPadding = PaddingValues(24.dp, 12.dp),
                )
                DdTextButton(
                    onClick = { navController.navigate("login") },
                    text = stringResource(R.string.alreadyHaveAccount)
                )
            }
        },
        containerColor = Color(255, 255, 255)
    )
}