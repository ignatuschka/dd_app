package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class GetUserActivityUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(activityId: Int): ActivityEntity = repository.getUserActivity(activityId)
}