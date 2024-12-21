package com.example.dd_app.data.database

import androidx.room.*
import com.example.dd_app.data.database.dao.MyActivityDao
import com.example.dd_app.data.database.dao.UserDao
import com.example.dd_app.data.database.model.MyActivityModel
import com.example.dd_app.data.database.model.UserModel

@Database(
    entities = [MyActivityModel::class, UserModel::class],
    version = 1,
    exportSchema = false
)
abstract class DdDatabase : RoomDatabase() {
    abstract fun myActivityDao() : MyActivityDao
    abstract fun userDao() : UserDao
}