package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val loginError: Boolean = true,
    val passError: Boolean = true,
    val pass: String = "",
    val login: String = "",
    val passVisible: Boolean = false,
    val showErrors: Boolean = false,
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onChangeLogin(loginText: String) {
        _uiState.update { it.copy(login = loginText, loginError = loginText.isEmpty()) }
    }

    fun onChangePass(passText: String) {
        _uiState.update { it.copy(pass = passText, passError = passText.isEmpty()) }
    }

    fun onPassVisibleChange() {
        _uiState.update { it.copy(passVisible = !it.passVisible) }
    }

    fun loginCallback() {
        _uiState.update { it.copy(showErrors = true) }
    }
}