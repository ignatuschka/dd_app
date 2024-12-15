package com.example.dd_app.data.data_source.local

import android.content.SharedPreferences
import javax.inject.Inject

interface AuthLocalDataSource {
    fun saveUserLogin(login: String)

    fun getUserLogin(): String?

    fun clearUserLogin()
}

class AuthLocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    AuthLocalDataSource {
    companion object {
        private const val KEY_USER_LOGIN = "user_login"
    }

    override fun saveUserLogin(login: String) =
        sharedPreferences.edit().putString(KEY_USER_LOGIN, login).apply()

    override fun getUserLogin(): String? = sharedPreferences.getString(KEY_USER_LOGIN, null)

    override fun clearUserLogin() =
        sharedPreferences.edit().remove(KEY_USER_LOGIN).apply()
}