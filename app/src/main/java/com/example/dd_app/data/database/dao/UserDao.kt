package com.example.dd_app.data.database.dao

import androidx.room.*
import com.example.dd_app.data.database.model.UserModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserModel): Long

    @Query("SELECT * FROM users WHERE login = :login")
    suspend fun getUserByLogin(login: String): UserModel?

    @Query("UPDATE users SET passwordHash = :newPasswordHash WHERE id = :userId")
    suspend fun updatePassword(userId: Int, newPasswordHash: String): Int

    @Query("UPDATE users SET login = :newLogin, userName = :newUserName WHERE id = :userId")
    suspend fun updateLoginAndUserName(userId: Int, newLogin: String, newUserName: String): Int
}