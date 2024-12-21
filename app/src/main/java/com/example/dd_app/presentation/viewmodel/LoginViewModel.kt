package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dd_app.core.util.HashUtil
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.SaveUserLoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginUiState(
    val loginError: Boolean = true,
    val passError: Boolean = true,
    val pass: String = "",
    val login: String = "",
    val passVisible: Boolean = false,
    val showErrors: Boolean = false,
    val wrongUserData: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserLoginUsecase: SaveUserLoginUsecase,
    private val getUserByLoginUsecase: GetUserByLoginUsecase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> get() = _navigateToMain

    fun onChangeLogin(loginText: String) = _uiState.update {
        it.copy(
            login = loginText,
            loginError = loginText.isEmpty(),
            wrongUserData = false
        )
    }

    fun onChangePass(passText: String) = _uiState.update {
        it.copy(
            pass = passText,
            passError = passText.isEmpty(),
            wrongUserData = false
        )
    }

    fun onPassVisibleChange() = _uiState.update { it.copy(passVisible = !it.passVisible) }

    suspend fun loginCallback() {
        if (uiState.value.loginError || uiState.value.passError) {
            _uiState.update {
                it.copy(showErrors = true)
            }
            return
        }
        val user = getUserByLoginUsecase(uiState.value.login)
        val passwordCorrect = user?.let {
            HashUtil.verifyHashPassword(uiState.value.pass, it.salt, it.passwordHash)
        } ?: false
        if (passwordCorrect) saveUserLoginUsecase(uiState.value.login)
        _uiState.update {
            it.copy(wrongUserData = !passwordCorrect, showErrors = true)
        }
        _navigateToMain.value = passwordCorrect
    }
}