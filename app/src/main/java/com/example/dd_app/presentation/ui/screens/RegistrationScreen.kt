package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.components.PolicyAndAgreementText
import com.example.dd_app.presentation.ui.components.RadioButtonGroup
import com.example.dd_app.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(popBack: () -> Unit, viewModel: RegistrationViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        containerColor = Color(255, 255, 255),
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
                    isError = uiState.loginError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
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
                    trailingIcon = if (uiState.passVisible)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = { viewModel.onPassVisibleChange() },
                    isError = uiState.passError && uiState.showErrors,
                    supportingText = stringResource(R.string.fillField),
                )
                DdTextField(
                    value = uiState.repeatPass,
                    visibleText = uiState.repeatPassVisible,
                    onValueChange = { viewModel.onChangeRepeatPass(it) },
                    label = stringResource(R.string.repeatPassword),
                    trailingIcon = if (uiState.repeatPassVisible)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = {viewModel.onRepeatPassVisibleChange()},
                    isError = uiState.repeatPassError && uiState.showErrors,
                    supportingText = stringResource(R.string.passwordsMustBeSame),
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.gender), fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default,
                    lineHeight = 24.sp,
                    color = Color(16, 16, 16),
                )
                RadioButtonGroup(
                    selectedOption = uiState.selectedOption,
                    onClick = { viewModel.onOptionSelected(it) },
                    radioOptions = uiState.radioOptions
                )
                DdButton(
                    onClick = { viewModel.signupCallback() },
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