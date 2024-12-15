package com.example.dd_app.data.repository

import com.example.dd_app.data.data_source.local.AuthLocalDataSource
import com.example.dd_app.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val datasource: AuthLocalDataSource) :
    AuthRepository {
    override fun saveUserLogin(login: String) = datasource.saveUserLogin(login)

    override fun getUserLogin(): String? = datasource.getUserLogin()

    override fun clearUserLogin() = datasource.clearUserLogin()
}