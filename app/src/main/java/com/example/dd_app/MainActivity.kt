package com.example.dd_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dd_app.presentation.ui.theme.Dd_appTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import com.example.dd_app.presentation.navigation.AppNavHost
import com.example.dd_app.presentation.ui.screens.LoginScreen
import com.example.dd_app.presentation.ui.screens.RegistrationScreen
import com.example.dd_app.presentation.ui.screens.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Dd_appTheme {
                DdApp()
            }
        }
    }
}

@Composable
fun DdApp() {
    AppNavHost()
}
