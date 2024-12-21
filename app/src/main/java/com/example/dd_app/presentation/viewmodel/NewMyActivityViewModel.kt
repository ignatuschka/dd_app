package com.example.dd_app.presentation.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.data.data_source.local.LocationClient
import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import com.example.dd_app.domain.usecase.InsertMyActivityUsecase
import com.yandex.mapkit.MapKit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class NewMyActivityUiState(
    val distanceMeters: Float = 0f,
    val exerciseStart: LocalDateTime = LocalDateTime.now(),
    val exerciseEnd: LocalDateTime = LocalDateTime.now(),
    val exerciseType: ExerciseType = ExerciseType.Bicycle,
    val isExerciseStarted: Boolean = false,
    val showDialog: Boolean = false
)

@HiltViewModel
class NewMyActivityViewModel @Inject constructor(
    private val mapKit: MapKit,
    private val insertMyActivityUsecase: InsertMyActivityUsecase,
    private val getUserByLoginUsecase: GetUserByLoginUsecase,
    private val getUserLoginUsecase: GetUserLoginUsecase,
    private val locationClient: LocationClient
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewMyActivityUiState())
    val uiState: StateFlow<NewMyActivityUiState> = _uiState.asStateFlow()
    private val _locationFlow = MutableStateFlow<Location?>(null)
    val locationFlow: StateFlow<Location?> = _locationFlow.asStateFlow()
    private var lastKnownLocation: Location? = null

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    init {
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        viewModelScope.launch {
            locationClient.getLocationUpdates().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = null
            ).collect {
                val distance = if (lastKnownLocation != null && it != null) lastKnownLocation!!.distanceTo(it) else 0f
                if (distance > 15) _uiState.update { state ->
                    state.copy(distanceMeters = state.distanceMeters + distance)
                }
                if (uiState.value.isExerciseStarted && lastKnownLocation != null) {
                    if (distance > 15) _locationFlow.emit(it)
                } else _locationFlow.emit(it)
                if (distance > 15 || lastKnownLocation == null) lastKnownLocation = it
            }
        }
    }


    fun onExerciseTypeSelect(type: ExerciseType) = _uiState.update { it.copy(exerciseType = type) }

    fun onShowDialog() = _uiState.update { it.copy(showDialog = true) }

    fun onCloseDialog() = _uiState.update { it.copy(showDialog = false) }


    fun onExerciseStart() =
        _uiState.update {
            it.copy(isExerciseStarted = true, exerciseStart = LocalDateTime.now())
        }


    suspend fun insertMyActivity() {
        val userLogin = getUserLoginUsecase()
        viewModelScope.launch {
            val user = if (userLogin != null) getUserByLoginUsecase(userLogin) else null
            val myActivity = if (user != null) ActivityEntity(
                userId = user.id,
                distanceMeters = uiState.value.distanceMeters.toInt(),
                exerciseStart = uiState.value.exerciseStart,
                exerciseEnd = LocalDateTime.now(),
                comment = "",
                exerciseType = uiState.value.exerciseType
            ) else null
            if (myActivity != null) insertMyActivityUsecase(myActivity)
        }
    }

    fun onStartMap() = mapKit.onStart()

    fun onStopMap() = mapKit.onStop()
}