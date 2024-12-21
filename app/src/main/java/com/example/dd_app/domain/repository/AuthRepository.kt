package com.example.dd_app.domain.repository

interface AuthRepository {
    fun saveUserLogin(login: String)

    fun getUserLogin(): String?

    fun clearUserLogin()
}