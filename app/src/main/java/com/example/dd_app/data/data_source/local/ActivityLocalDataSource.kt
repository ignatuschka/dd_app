package com.example.dd_app.data.data_source.local

import com.example.dd_app.data.database.dao.MyActivityDao
import com.example.dd_app.data.database.model.MyActivityModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ActivityLocalDataSource {

    fun getAllMyActivities(userId: Int): Flow<List<MyActivityModel>>

    suspend fun insertMyActivity(item: MyActivityModel): Long

    suspend fun deleteMyActivity(activityId: Int): Int

    suspend fun getMyActivityById(id: Int): MyActivityModel?

    suspend fun updateComment(activityId: Int, comment: String): Int
}

class ActivityLocalDataSourceImpl @Inject constructor(private val dao: MyActivityDao) :
    ActivityLocalDataSource {

    override fun getAllMyActivities(userId: Int): Flow<List<MyActivityModel>> = dao.getAllMyActivities(userId)

    override suspend fun insertMyActivity(item: MyActivityModel): Long = dao.insertMyActivity(item)

    override suspend fun deleteMyActivity(activityId: Int): Int = dao.deleteMyActivity(activityId)

    override suspend fun getMyActivityById(id: Int): MyActivityModel? = dao.getMyActivityById(id)

    override suspend fun updateComment(activityId: Int, comment: String): Int = dao.updateComment(activityId, comment)
}