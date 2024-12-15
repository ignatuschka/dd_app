package com.example.dd_app.data.network.model

import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.domain.entity.ActivityEntity
import com.google.gson.annotations.SerializedName

data class UserActivityModel(
    val id: Int,
    @SerializedName("distance_meters")
    val distanceMeters: Int,
    @SerializedName("exercise_start")
    val exerciseStart: String,
    @SerializedName("exercise_end")
    val exerciseEnd: String,
    val comment: String,
    @SerializedName("exercise_type")
    val exerciseType: String,
    @SerializedName("user_name")
    val userName: String
) {
    fun toEntity() = ActivityEntity(
        id = id,
        distanceMeters = distanceMeters,
        exerciseStart = DateConvert.stringToDate(exerciseStart),
        exerciseEnd = DateConvert.stringToDate(exerciseEnd),
        comment = comment,
        exerciseType = ExerciseType.valueOf(exerciseType),
        userName = userName
    )
}