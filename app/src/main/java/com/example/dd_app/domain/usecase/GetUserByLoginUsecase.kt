package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.entity.UserEntity
import com.example.dd_app.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByLoginUsecase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(login: String): UserEntity? =
        repository.getUserByLogin(login)
}