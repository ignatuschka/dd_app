package com.example.dd_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dd_app.R
import com.example.dd_app.ui.components.DdAppBar
import com.example.dd_app.ui.components.DdButton
import com.example.dd_app.ui.components.DdTextField

@Composable
fun LoginScreen(navController: NavHostController) {
    val loginError = remember { mutableStateOf(true) }
    val passError = remember { mutableStateOf(true) }
    val pass = remember { mutableStateOf("") }
    val login = remember { mutableStateOf("") }
    val passVisible = remember { mutableStateOf(false) }
    val showErrors = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            DdAppBar(navController = navController, title = stringResource(R.string.signIn))
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            16.dp,
                            padding.calculateTopPadding(),
                            16.dp,
                            padding.calculateBottomPadding()
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .size(271.52.dp, 240.dp)
                        .padding(top = 24.dp, bottom = 16.dp),
                    painter = painterResource(id = R.drawable.welcome_screen_image),
                    contentDescription = null
                )
                DdTextField(
                    value = login.value,
                    visibleText = true,
                    onValueChange = { v -> login.value = v; loginError.value = v.isEmpty() },
                    label = stringResource(R.string.login),
                    trailingIcon = null,
                    isError = loginError.value && showErrors.value,
                    supportingText = stringResource(R.string.fillField),
                    onClick = null,
                )
                DdTextField(
                    value = pass.value,
                    visibleText = passVisible.value,
                    onValueChange = { v -> pass.value = v; passError.value = v.isEmpty() },
                    label = "Пароль",
                    trailingIcon = if (passVisible.value)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = { passVisible.value = !passVisible.value },
                    isError = passError.value && showErrors.value,
                    supportingText = stringResource(R.string.fillField),
                )
                DdButton(
                    onClick = { showErrors.value = true }, text = stringResource(R.string.signin),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                )
            }
        },
        containerColor = Color(255, 255, 255)
    )
}