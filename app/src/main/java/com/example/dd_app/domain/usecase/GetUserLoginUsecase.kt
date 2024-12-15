package com.example.dd_app.domain.usecase

import com.example.dd_app.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserLoginUsecase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(): String? = repository.getUserLogin()
}