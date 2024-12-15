package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.ActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class InsertMyActivityUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(item: ActivityEntity): Boolean = repository.insertMyActivity(item)
}