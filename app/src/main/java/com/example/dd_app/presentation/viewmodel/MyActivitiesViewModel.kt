package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.entity.UserEntity
import com.example.dd_app.domain.provider.ResourceProvider
import com.example.dd_app.domain.usecase.GetAllMyActivitiesUsecase
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class MyActivitiesViewModel @Inject constructor(
    private val getAllMyActivitiesUsecase: GetAllMyActivitiesUsecase,
    private val getUserByLoginUsecase: GetUserByLoginUsecase,
    private val resourceProvider: ResourceProvider,
    private val getUserLoginUsecase: GetUserLoginUsecase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(emptyList<ActivitiesWithTitles>())
    val uiState: StateFlow<List<ActivitiesWithTitles>> = _uiState.asStateFlow()

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    init {
        val userLogin = getUserLoginUsecase()
        if (userLogin != null)
            viewModelScope.launch {
                val user = getUserByLoginUsecase(userLogin)
                if (user != null) _uiState.emitAll(
                    getAllMyActivitiesUsecase(user.id).map { groupActivitiesByDate(it) }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = emptyList()
                        )
                )
            }

    }

    private fun groupActivitiesByDate(activities: List<MyActivityEntity>): List<ActivitiesWithTitles> {
        val now = LocalDateTime.now()
        val oneMonthsAgo = YearMonth.from(now).minusMonths(1).atDay(1).atStartOfDay()
        var date: LocalDate? = null
        val result = mutableListOf<ActivitiesWithTitles>()
        activities.forEach {
            if (it.exerciseStart.toLocalDate() == now.toLocalDate() && it.exerciseStart.toLocalDate() != date)
                result.add(
                    ActivitiesWithTitles(it, resourceProvider.getString(R.string.today))
                ) else if (it.exerciseStart.toLocalDate() == now.minusDays(1)
                    .toLocalDate() && it.exerciseStart.toLocalDate() != date
            ) result.add(
                ActivitiesWithTitles(it, resourceProvider.getString(R.string.yesterday))
            ) else if (it.exerciseStart.month == now.month && it.exerciseStart.year == now.year && it.exerciseStart.toLocalDate() != date) result.add(
                ActivitiesWithTitles(it, DateConvert().format(it.exerciseStart, "d MMMM"))
            ) else if (it.exerciseStart.isBefore(oneMonthsAgo) && (it.exerciseStart.month != date?.month || it.exerciseStart.year != date?.year)) result.add(
                ActivitiesWithTitles(it, DateConvert().format(it.exerciseStart, "LLLL yyyy года"))
            ) else result.add(ActivitiesWithTitles(it))
            date = it.exerciseStart.toLocalDate()
        }
        return result
    }
}
