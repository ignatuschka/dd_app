package com.example.dd_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.provider.ResourceProvider
import com.example.dd_app.domain.usecase.GetAllUserActivitiesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject

data class UsersActivitiesUiState(
    val items: List<ActivitiesWithTitles> = emptyList(),
    val isLoading: Boolean = false
)

data class ActivitiesWithTitles(
    val item: MyActivityEntity,
    val title: String? = null
)

@HiltViewModel
class UsersActivitiesViewModel @Inject constructor(
    private val getAllUserActivitiesUsecase: GetAllUserActivitiesUsecase,
    private val resourceProvider: ResourceProvider,
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UsersActivitiesUiState())
    val uiState: StateFlow<UsersActivitiesUiState> = _uiState.asStateFlow()

    init {
        updateUserActivities()
    }

    fun updateUserActivities() {
        if (!_uiState.value.isLoading) viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val activities = getAllUserActivitiesUsecase()
            val result = groupActivitiesByDate(activities)
            _uiState.update { it.copy(isLoading = false, items = result) }
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