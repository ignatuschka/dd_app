package com.example.dd_app.domain.entity

import com.example.dd_app.data.database.model.UserModel

data class UserEntity(
    val id: Int,
    val login: String,
    val username: String,
    val passwordHash: String,
    val salt: String
) {
    fun toModel() =
        UserModel(login = login, username = username, passwordHash = passwordHash, salt = salt)
}