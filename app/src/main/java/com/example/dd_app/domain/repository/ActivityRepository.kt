package com.example.dd_app.domain.repository

import com.example.dd_app.domain.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    fun getAllMyActivities(userId: Int): Flow<List<ActivityEntity>>

    suspend fun insertMyActivity(item: ActivityEntity): Boolean

    suspend fun deleteMyActivity(activityId: Int): Boolean

    suspend fun getMyActivityById(id: Int): ActivityEntity?

    suspend fun updateComment(activityId: Int, comment: String): Boolean

    suspend fun getAllUserActivities(): List<ActivityEntity>

    suspend fun getUserActivity(activityId: Int): ActivityEntity
}