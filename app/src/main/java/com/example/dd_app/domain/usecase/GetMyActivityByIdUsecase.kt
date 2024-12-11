package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import javax.inject.Inject

class GetMyActivityByIdUsecase @Inject constructor(private val repository: ActivityRepository) {
    suspend operator fun invoke(id: Int) : MyActivityEntity? = repository.getMyActivityById(id)
}