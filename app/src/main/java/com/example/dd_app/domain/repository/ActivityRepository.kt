package com.example.dd_app.domain.repository

import com.example.dd_app.domain.entity.MyActivityEntity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    fun getAllMyActivities(userId: Int): Flow<List<MyActivityEntity>>

    suspend fun insertMyActivity(item: MyActivityEntity): Boolean

    suspend fun deleteMyActivity(activityId: Int): Boolean

    suspend fun getMyActivityById(id: Int): MyActivityEntity?

    suspend fun updateComment(activityId: Int, comment: String): Boolean

    suspend fun getAllUserActivities(): List<MyActivityEntity>

    suspend fun getUserActivity(activityId: Int): MyActivityEntity
}