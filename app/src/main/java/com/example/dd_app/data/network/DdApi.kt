package com.example.dd_app.data.network

import com.example.dd_app.data.network.model.UserActivityModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DdApi {
    @GET("activities/all")
    suspend fun getAllUserActivities(): List<UserActivityModel>

    @GET("activities/{id}")
    suspend fun getUserActivity(@Path("id") activityId: Int): UserActivityModel
}