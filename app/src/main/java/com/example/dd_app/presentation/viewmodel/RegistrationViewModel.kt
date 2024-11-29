package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dd_app.R
import com.example.dd_app.domain.provider.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class RegistrationUiState(
    val loginError: Boolean = true,
    val passError: Boolean = true,
    val pass: String = "",
    val login: String = "",
    val passVisible: Boolean = false,
    val showErrors: Boolean = false,
    val name: String = "",
    val repeatPass: String = "",
    val repeatPassVisible: Boolean = false,
    val radioOptions: List<String> = listOf(),
    val selectedOption: String = "",
    val repeatPassError: Boolean = true,
    val nameError: Boolean = true,
)

@HiltViewModel
class RegistrationViewModel @Inject  constructor(private val resourceProvider: ResourceProvider) : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegistrationUiState(
            radioOptions = listOf(
                resourceProvider.getString(R.string.male),
                resourceProvider.getString(R.string.female),
                resourceProvider.getString(R.string.other)
            )
        )
    )
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun onOptionSelected(option: String) {
        _uiState.update { it.copy(selectedOption = option) }
    }

    fun onChangeLogin(loginText: String) {
        _uiState.update { it.copy(login = loginText, loginError = loginText.isEmpty()) }
    }

    fun onChangeName(nameText: String) {
        _uiState.update { it.copy(name = nameText, nameError = nameText.isEmpty()) }
    }

    fun onChangePass(passText: String) {
        _uiState.update { it.copy(pass = passText, passError = passText.isEmpty()) }
    }

    fun onChangeRepeatPass(repeatPassText: String) {
        _uiState.update {
            it.copy(
                repeatPass = repeatPassText,
                repeatPassError = repeatPassText.isEmpty() || it.pass != repeatPassText
            )
        }
    }

    fun onPassVisibleChange() {
        _uiState.update { it.copy(passVisible = !it.passVisible) }
    }

    fun onRepeatPassVisibleChange() {
        _uiState.update { it.copy(repeatPassVisible = !it.repeatPassVisible) }
    }

    fun signupCallback() {
        _uiState.update { it.copy(showErrors = true) }
    }
}