package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.UserRepository
import javax.inject.Inject

class UpdatePasswordUsecase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userId: Int, newPasswordHash: String): Boolean =
        repository.updatePassword(userId, newPasswordHash)
}