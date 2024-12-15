package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val getUserLoginUsecase: GetUserLoginUsecase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    init {
        checkUserLogin()
    }

    private fun checkUserLogin() {
        val userLogin = getUserLoginUsecase()
        _uiState.update { userLogin != null }
    }
}