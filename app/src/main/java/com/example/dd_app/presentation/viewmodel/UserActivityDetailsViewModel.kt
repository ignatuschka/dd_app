package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.usecase.GetUserActivityUsecase
import com.example.dd_app.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserActivityDetailsUiState(
    val userActivity: MyActivityEntity? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class UserActivityDetailsViewModel @Inject constructor(
    private val getUserActivityUsecase: GetUserActivityUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserActivityDetailsUiState())
    val uiState: StateFlow<UserActivityDetailsUiState> = _uiState.asStateFlow()
    val activityId: Int? = savedStateHandle[Screen.UserActivityDetails.argActivityId]

    init {
        getUserActivity()
    }

    fun getUserActivity() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val userActivity = if (activityId != null) getUserActivityUsecase(activityId) else null
            _uiState.update { it.copy(isLoading = false, userActivity = userActivity) }
        }
    }
}