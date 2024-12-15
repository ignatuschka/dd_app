package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.convert.timeDifference
import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.usecase.DeleteMyActivityUsecase
import com.example.dd_app.domain.usecase.GetMyActivityByIdUsecase
import com.example.dd_app.domain.usecase.UpdateCommentUsecase
import com.example.dd_app.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MyActivityDetailsUiState(
    val myActivity: ActivityEntity? = null,
    val comment: String = ""
)

@HiltViewModel
class MyActivityDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMyActivityByIdUsecase: GetMyActivityByIdUsecase,
    private val deleteMyActivityUsecase: DeleteMyActivityUsecase,
    private val updateCommentUsecase: UpdateCommentUsecase
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(MyActivityDetailsUiState())
    val uiState: StateFlow<MyActivityDetailsUiState> = _uiState.asStateFlow()
    val activityId: Int? = savedStateHandle[Screen.MyActivityDetails.argActivityId]

    init {
        viewModelScope.launch {
            val userActivity =
                if (activityId != null) getMyActivityByIdUsecase(activityId) else null
            _uiState.update { it.copy(myActivity = userActivity, comment = userActivity?.comment ?: "") }
        }
    }

    suspend fun deleteMyActivity(): Boolean {
        return if (activityId != null) deleteMyActivityUsecase(activityId) else false
    }

    fun onChangeComment(comment: String) {
        _uiState.update { it.copy(comment = comment) }
    }

    suspend fun updateComment() {
        if (activityId != null &&
            _uiState.value.comment != (_uiState.value.myActivity?.comment ?: "")
        ) updateCommentUsecase(activityId, _uiState.value.comment)
    }

    fun generateShareText(): String? {
        val myActivity = _uiState.value.myActivity ?: return null
        val distance = if (myActivity.distanceMeters > 1000)
            "${myActivity.distanceMeters.toDouble() / 1000} км"
        else
            "${myActivity.distanceMeters} м"

        val duration =
            myActivity.exerciseStart.timeDifference(myActivity.exerciseEnd)
        val startTime = DateConvert.format(myActivity.exerciseStart, "HH:mm")
        val endTime = DateConvert.format(myActivity.exerciseEnd, "HH:mm")
        val comment = myActivity.comment.ifBlank { "Без комментариев" }
        val exerciseType = myActivity.exerciseType.title

        return """
        Тип тренировки: $exerciseType
        Дистанция: $distance
        Продолжительность: $duration
        Старт: $startTime
        Финиш: $endTime
        Комментарий: $comment
    """.trimIndent()
    }
}