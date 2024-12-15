package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.dd_app.presentation.navigation.BottomNavItems
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdButton
import com.example.dd_app.presentation.ui.components.DdTextButton
import com.example.dd_app.presentation.ui.components.DdTextField
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    logout: () -> Unit,
    navToChangePasswordScreen: () -> Unit
) {
    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()
    val modifier = Modifier.padding(horizontal = 16.dp)

    Column {
        DdAppBar(
            windowInsets = WindowInsets(0.dp),
            title = BottomNavItems.Profile.label,
            actions = {
                DdTextButton(
                    text = stringResource(R.string.save),
                    onClick = { profileViewModel.updateLoginAndUserName() })
            })
        DdTextField(
            modifier = modifier,
            value = uiState.login,
            onValueChange = { profileViewModel.onChangeLogin(it) },
            label = stringResource(R.string.login),
            isError = uiState.loginError || uiState.wrongUserData,
            supportingText = if (uiState.loginError) stringResource(R.string.fillField)
            else if (uiState.wrongUserData) stringResource(
                R.string.userExistErrorText
            ) else null
        )
        DdTextField(
            modifier = modifier,
            value = uiState.name,
            onValueChange = { profileViewModel.onChangeName(it) },
            label = stringResource(R.string.nameOrNickname),
            isError = uiState.nameError,
            supportingText = if (uiState.nameError) {
                stringResource(R.string.fillField)
            } else null,
        )
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            DdTextButton(modifier = modifier, text = stringResource(R.string.changePassword), onClick = navToChangePasswordScreen)
            DdButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(R.string.logout),
                color = Colors.Error,
                onClick = {
                    profileViewModel.logoutCallback()
                    logout()
                }
            )
        }
    }
}