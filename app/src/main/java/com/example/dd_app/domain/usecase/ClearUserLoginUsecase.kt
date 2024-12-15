package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.AuthRepository
import javax.inject.Inject

class ClearUserLoginUsecase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.clearUserLogin()
}