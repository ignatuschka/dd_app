package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.*
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun WelcomeScreen(navToRegistration: () -> Unit, navToLogin: () -> Unit) {
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
                    modifier = Modifier.padding(horizontal = 16.dp),
                    painter = painterResource(id = R.drawable.welcome_screen_image),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(PaddingValues(0.dp, 32.dp, 0.dp, 16.dp)),
                    text = stringResource(R.string.welcomeScreenTitle),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.welcomeScreenSubtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Colors.Gray,
                )
                DdButton(
                    onClick = navToRegistration,
                    text = stringResource(R.string.signUp),
                    modifier = Modifier.padding(PaddingValues(0.dp, 32.dp, 0.dp, 16.dp)),
                    contentPadding = PaddingValues(24.dp, 12.dp),
                )
                DdTextButton(
                    onClick = navToLogin,
                    text = stringResource(R.string.alreadyHaveAccount)
                )
            }
        },
        containerColor = Colors.White
    )
}