package com.example.dd_app.data.repository

import com.example.dd_app.data.data_source.local.UserLocalDataSource
import com.example.dd_app.domain.entity.UserEntity
import com.example.dd_app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val datasource: UserLocalDataSource) :
    UserRepository {
    override suspend fun insertUser(user: UserEntity): Boolean =
        datasource.insertUser(user.toModel()) != -1L

    override suspend fun getUserByLogin(login: String): UserEntity? =
        datasource.getUserByLogin(login)?.toEntity()

    override suspend fun updatePassword(
        userId: Int,
        newPasswordHash: String
    ): Boolean = datasource.updatePassword(userId, newPasswordHash) > 0

    override suspend fun updateLoginAndUserName(
        userId: Int,
        newLogin: String,
        newUserName: String
    ): Boolean = datasource.updateLoginAndUserName(userId, newLogin, newUserName) > 0
}