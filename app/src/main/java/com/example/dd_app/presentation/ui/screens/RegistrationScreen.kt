package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.PolicyAndAgreementText
import com.example.dd_app.presentation.ui.components.RadioButtonGroup
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.RegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    popBack: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel(),
    navToMain: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigateToHome by viewModel.navigateToMain.observeAsState()


    Scaffold(
        containerColor = Colors.White,
        topBar = {
            DdAppBar(popBack = popBack, title = stringResource(R.string.registration))
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
                    )
            ) {
                DdTextField(
                    value = uiState.login,
                    onValueChange = { viewModel.onChangeLogin(it) },
                    label = stringResource(R.string.login),
                    isError = uiState.loginError && uiState.showErrors || uiState.wrongUserData,
                    supportingText = if (uiState.passError && uiState.showErrors) {
                        stringResource(R.string.fillField)
                    } else if (uiState.wrongUserData) {
                        stringResource(R.string.userExistErrorText)
                    } else null,
                )
                DdTextField(
                    value = uiState.name,
                    onValueChange = { viewModel.onChangeName(it) },
                    label = stringResource(R.string.nameOrNickname),
                    isError = uiState.nameError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
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
                    isError = uiState.passError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
                )
                DdTextField(
                    value = uiState.repeatPass,
                    visibleText = uiState.repeatPassVisible,
                    onValueChange = { viewModel.onChangeRepeatPass(it) },
                    label = stringResource(R.string.repeatPassword),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onRepeatPassVisibleChange() }) {
                            Icon(
                                imageVector = if (uiState.repeatPassVisible)
                                    Icons.Filled.VisibilityOff
                                else Icons.Filled.Visibility, null
                            )
                        }
                    },
                    isError = uiState.repeatPassError && uiState.showErrors,
                    supportingText = stringResource(R.string.passwordsMustBeSame),
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.gender),
                    style = MaterialTheme.typography.titleLarge,
                    color = Colors.Dark,
                )
                RadioButtonGroup(
                    selectedOption = uiState.selectedOption,
                    onClick = { viewModel.onOptionSelected(it) },
                    radioOptions = uiState.radioOptions
                )
                DdButton(
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.signupCallback()
                            navigateToHome?.let {
                                if (it) navToMain()
                            }
                        }
                    },
                    text = stringResource(R.string.signUp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 24.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                )
                PolicyAndAgreementText()
            }
        },
    )
}