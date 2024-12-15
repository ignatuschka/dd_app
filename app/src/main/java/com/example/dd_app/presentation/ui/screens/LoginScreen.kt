package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    popBack: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    navToMain: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigateToMain by viewModel.navigateToMain.observeAsState()

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
                    modifier = Modifier.padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        start = 70.dp,
                        end = 70.dp
                    ),
                    painter = painterResource(id = R.drawable.welcome_screen_image),
                    contentDescription = null
                )
                DdTextField(
                    value = uiState.login,
                    onValueChange = { viewModel.onChangeLogin(it) },
                    label = stringResource(R.string.login),
                    isError = uiState.loginError && uiState.showErrors || uiState.wrongUserData,
                    supportingText = if (uiState.loginError && uiState.showErrors) {
                        stringResource(R.string.fillField)
                    } else null,
                )
                DdTextField(
                    value = uiState.pass,
                    visibleText = uiState.passVisible,
                    onValueChange = { viewModel.onChangePass(it) },
                    label = stringResource(R.string.password),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onPassVisibleChange() }) {
                            Icon(
                                imageVector = if (uiState.passVisible)
                                    Icons.Filled.VisibilityOff
                                else Icons.Filled.Visibility, null
                            )
                        }
                    },
                    isError = uiState.passError && uiState.showErrors || uiState.wrongUserData,
                    supportingText = if (uiState.passError && uiState.showErrors) {
                        stringResource(R.string.fillField)
                    } else if (uiState.wrongUserData) {
                        stringResource(
                            R.string.wrongLoginOrPass
                        )
                    } else null,
                )
                DdButton(
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.loginCallback()
                            navigateToMain?.let {
                                if (it) navToMain()
                            }
                        }
                    }, text = stringResource(R.string.signin),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                )
            }
        },
        containerColor = Colors.White
    )
}