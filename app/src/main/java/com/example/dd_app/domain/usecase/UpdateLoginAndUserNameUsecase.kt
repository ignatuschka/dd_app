package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.UserRepository
import javax.inject.Inject

class UpdateLoginAndUserNameUsecase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userId: Int, newLogin: String, newUserName: String): Boolean =
        repository.updateLoginAndUserName(userId, newLogin, newUserName)
}