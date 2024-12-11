package com.example.dd_app.data.data_source.remote

import com.example.dd_app.data.network.DdApi
import com.example.dd_app.data.network.model.UserActivityModel
import javax.inject.Inject

interface ActivityRemoteDataSource {
    suspend fun getAllUserActivities(): List<UserActivityModel>

    suspend fun getUserActivity(activityId: Int): UserActivityModel
}

class ActivityRemoteDataSourceImpl @Inject constructor(private val api: DdApi) :
    ActivityRemoteDataSource {
    override suspend fun getAllUserActivities(): List<UserActivityModel> =
        api.getAllUserActivities()

    override suspend fun getUserActivity(activityId: Int): UserActivityModel =
        api.getUserActivity(activityId)
}