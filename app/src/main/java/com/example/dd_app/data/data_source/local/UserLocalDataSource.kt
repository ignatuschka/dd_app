package com.example.dd_app.data.data_source.local

import com.example.dd_app.data.database.dao.UserDao
import com.example.dd_app.data.database.model.UserModel
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun insertUser(user: UserModel): Long

    suspend fun getUserByLogin(login: String): UserModel?

    suspend fun updatePassword(userId: Int, newPasswordHash: String): Int

    suspend fun updateLoginAndUserName(userId: Int, newLogin: String, newUserName: String): Int
}

class UserLocalDataSourceImpl @Inject constructor(private val dao: UserDao) : UserLocalDataSource {
    override suspend fun insertUser(user: UserModel): Long = dao.insertUser(user)

    override suspend fun getUserByLogin(login: String): UserModel? = dao.getUserByLogin(login)

    override suspend fun updatePassword(
        userId: Int,
        newPasswordHash: String
    ): Int = dao.updatePassword(userId, newPasswordHash)

    override suspend fun updateLoginAndUserName(
        userId: Int,
        newLogin: String,
        newUserName: String
    ): Int = dao.updateLoginAndUserName(userId, newLogin, newUserName)
}