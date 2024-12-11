package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.core.util.HashUtil
import com.example.dd_app.domain.entity.UserEntity
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import com.example.dd_app.domain.usecase.UpdatePasswordUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChangePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordRepeat: String = "",
    val oldPasswordError: Boolean = true,
    val newPasswordError: Boolean = true,
    val newPasswordRepeatError: Boolean = true,
    val wrongUserData: Boolean = false,
    val showErrors: Boolean = false
)

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val getUserLoginUsecase: GetUserLoginUsecase,
    private val getUserByLoginUsecase: GetUserByLoginUsecase,
    private val updatePasswordUsecase: UpdatePasswordUsecase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChangePasswordUiState())
    val uiState: StateFlow<ChangePasswordUiState> = _uiState.asStateFlow()
    private val hashUtil = HashUtil()
    private var user: UserEntity? = null

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    private suspend fun getUser() {
        val login = getUserLoginUsecase()
        if (login != null) user = getUserByLoginUsecase(login)
    }

    fun onChangeOldPassword(oldPasswordText: String) {
        _uiState.update {
            it.copy(
                oldPassword = oldPasswordText,
                oldPasswordError = oldPasswordText.isEmpty(),
                wrongUserData = false
            )
        }
    }

    fun onChangeNewPassword(newPasswordText: String) {
        _uiState.update {
            it.copy(
                newPassword = newPasswordText,
                newPasswordError = newPasswordText.isEmpty(),
            )
        }
    }

    fun onChangeNewPasswordRepeat(newPasswordRepeatText: String) {
        _uiState.update {
            it.copy(
                newPasswordRepeat = newPasswordRepeatText,
                newPasswordRepeatError = newPasswordRepeatText.isEmpty() || it.newPassword != newPasswordRepeatText,
            )
        }
    }

    fun updateCallback() {
        if (uiState.value.oldPasswordError || uiState.value.newPasswordError || uiState.value.newPasswordRepeatError || uiState.value.wrongUserData || user == null) {
            _uiState.update {
                it.copy(showErrors = true)
            }
            return
        }
        val passwordCorrect = user?.let {
            hashUtil.verifyHashValue(
                value = uiState.value.oldPassword,
                salt = it.salt,
                storedHash = it.passwordHash
            )
        } ?: false
        viewModelScope.launch {
            val isUpdated = if (passwordCorrect)  updatePasswordUsecase(
                user!!.id,
                hashUtil.hashValue(uiState.value.newPassword, user!!.salt)
            ) else false
            if (isUpdated) {
                getUser()
                _uiState.update {
                    it.copy(
                        oldPassword = "",
                        newPassword = "",
                        newPasswordRepeat = ""
                    )
                }
            }
            _uiState.update { it.copy(wrongUserData = !isUpdated) }
        }
    }
}