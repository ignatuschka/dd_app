package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class GetAllUserActivitiesUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(): List<ActivityEntity> = repository.getAllUserActivities()
}