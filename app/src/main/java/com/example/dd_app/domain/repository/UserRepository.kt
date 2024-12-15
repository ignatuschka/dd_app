package com.example.dd_app.domain.repository

import com.example.dd_app.domain.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity): Boolean

    suspend fun getUserByLogin(login: String): UserEntity?

    suspend fun updatePassword(userId: Int, newPasswordHash: String): Boolean

    suspend fun updateLoginAndUserName(userId: Int, newLogin: String, newUserName: String): Boolean
}