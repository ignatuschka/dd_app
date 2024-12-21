package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.domain.entity.UserEntity
import com.example.dd_app.domain.usecase.ClearUserLoginUsecase
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import com.example.dd_app.domain.usecase.SaveUserLoginUsecase
import com.example.dd_app.domain.usecase.UpdateLoginAndUserNameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val login: String = "",
    val name: String = "",
    val loginError: Boolean = false,
    val nameError: Boolean = false,
    val wrongUserData: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserLoginUsecase: GetUserLoginUsecase,
    private val getUserByLoginUsecase: GetUserByLoginUsecase,
    private val updateLoginAndUserNameUsecase: UpdateLoginAndUserNameUsecase,
    private val clearUserLoginUsecase: ClearUserLoginUsecase,
    private val saveUserLoginUsecase: SaveUserLoginUsecase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    private var user: UserEntity? = null

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    private suspend fun getUser() {
        val login = getUserLoginUsecase()
        if (login != null) user = getUserByLoginUsecase(login)
        if (user != null) _uiState.update {
            it.copy(
                login = user!!.login,
                name = user!!.username
            )
        }
    }

    fun onChangeLogin(loginText: String) {
        _uiState.update {
            it.copy(
                login = loginText,
                loginError = loginText.isEmpty(),
                wrongUserData = false
            )
        }
    }

    fun onChangeName(nameText: String) {
        _uiState.update { it.copy(name = nameText, nameError = nameText.isEmpty()) }
    }

    fun updateLoginAndUserName() {
        if (user != null && uiState.value.login.isNotEmpty() && uiState.value.name.isNotEmpty() && (uiState.value.login != user?.login || uiState.value.name != user?.username)) {
            viewModelScope.launch {
                val isUpdated = updateLoginAndUserNameUsecase(
                    user!!.id,
                    uiState.value.login,
                    uiState.value.name
                )
                if (!isUpdated) {
                    _uiState.update {
                        it.copy(
                            login = user!!.login,
                            name = user!!.username,
                            wrongUserData = true
                        )
                    }
                } else {
                    clearUserLoginUsecase()
                    saveUserLoginUsecase(uiState.value.login)
                    getUser()
                }
            }
        }
    }

    fun logoutCallback() {
        clearUserLoginUsecase()
    }
}