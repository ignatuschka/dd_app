package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class InsertMyActivityUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(item: MyActivityEntity): Boolean = repository.insertMyActivity(item)
}