package com.example.dd_app.domain.entity

import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.data.database.model.MyActivityModel
import java.time.LocalDateTime

data class MyActivityEntity(
    val id: Int = -1,
    val userId: Int = -1,
    val distanceMeters: Int,
    val exerciseStart: LocalDateTime,
    val exerciseEnd: LocalDateTime,
    val comment: String,
    val exerciseType: ExerciseType,
    val userName: String? = null
) {
    fun toModel() = MyActivityModel(
        distanceMeters = distanceMeters,
        userId = userId,
        exerciseStart = DateConvert().dateToString(exerciseStart),
        exerciseEnd = DateConvert().dateToString(exerciseEnd),
        comment = comment,
        exerciseType = exerciseType.name,
    )
}