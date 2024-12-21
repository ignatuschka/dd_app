package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.viewmodel.ChangePasswordViewModel

@Composable
fun ChangePasswordScreen(
    popBack: () -> Unit,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val modifier = Modifier.padding(horizontal = 16.dp)

    Column {
        DdAppBar(
            windowInsets = WindowInsets(0.dp),
            popBack = popBack,
            title = stringResource(R.string.changePassword)
        )
        DdTextField(
            modifier = modifier,
            value = uiState.oldPassword,
            onValueChange = { viewModel.onChangeOldPassword(it) },
            label = stringResource(R.string.oldPassword),
            isError = uiState.oldPasswordError && uiState.showErrors || uiState.wrongUserData,
            supportingText = if (uiState.oldPasswordError) stringResource(R.string.fillField)
            else if (uiState.wrongUserData) stringResource(R.string.wrongPassword) else null
        )
        DdTextField(
            modifier = modifier,
            value = uiState.newPassword,
            onValueChange = { viewModel.onChangeNewPassword(it) },
            label = stringResource(R.string.newPassword),
            isError = uiState.newPasswordError && uiState.showErrors,
            supportingText = stringResource(R.string.fillField),
        )
        DdTextField(
            modifier = modifier.padding(top = 16.dp),
            value = uiState.newPasswordRepeat,
            onValueChange = { viewModel.onChangeNewPasswordRepeat(it) },
            label = stringResource(R.string.repeatPassword),
            isError = uiState.newPasswordRepeatError && uiState.showErrors,
            supportingText = stringResource(R.string.passwordsMustBeSame),
        )
        DdButton(modifier = modifier.fillMaxWidth(), text = stringResource(R.string.accept), onClick = { viewModel.updateCallback() })
    }
}