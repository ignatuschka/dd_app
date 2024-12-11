package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class GetUserActivityUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(activityId: Int): MyActivityEntity = repository.getUserActivity(activityId)
}