package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMyActivitiesUsecase @Inject constructor(private val repository: ActivityRepository) {
    operator fun invoke(userId: Int): Flow<List<ActivityEntity>> = repository.getAllMyActivities(userId)
}