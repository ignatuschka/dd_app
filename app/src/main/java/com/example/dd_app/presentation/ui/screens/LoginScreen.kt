package com.example.dd_app.presentation.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(popBack: () -> Unit, viewModel: LoginViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            DdAppBar(popBack = popBack, title = stringResource(R.string.signIn))
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
                    value = uiState.login,
                    onValueChange = { viewModel.onChangeLogin(it) },
                    label = stringResource(R.string.login),
                    isError = uiState.loginError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
                )
                DdTextField(
                    value = uiState.pass,
                    visibleText = uiState.passVisible,
                    onValueChange = { viewModel.onChangePass(it) },
                    label = stringResource(R.string.password),
                    trailingIcon = if (uiState.passVisible)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = { viewModel.onPassVisibleChange() },
                    isError = uiState.passError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
                )
                DdButton(
                    onClick = { viewModel.loginCallback() }, text = stringResource(R.string.signin),
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