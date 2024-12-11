package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class UpdateCommentUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(activityId: Int, comment: String): Boolean =
        repository.updateComment(activityId, comment)
}