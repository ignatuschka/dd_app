package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class DeleteMyActivityUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(activityId: Int): Boolean = repository.deleteMyActivity(activityId)
}