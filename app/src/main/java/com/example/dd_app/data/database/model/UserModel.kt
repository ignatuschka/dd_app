package com.example.dd_app.data.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.dd_app.domain.entity.UserEntity

@Entity(tableName = "users", indices = [Index(value = ["login"], unique = true)])
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val username: String,
    val passwordHash: String,
    val salt: String
) {
    fun toEntity() =
        UserEntity(
            id = id,
            login = login,
            username = username,
            passwordHash = passwordHash,
            salt = salt
        )
}