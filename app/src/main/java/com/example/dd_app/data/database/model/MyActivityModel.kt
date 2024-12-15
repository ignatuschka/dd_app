package com.example.dd_app.data.database.model

import androidx.room.*
import com.example.dd_app.core.convert.DateConvert
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.domain.entity.ActivityEntity

@Entity(
    tableName = "my_activities",
    foreignKeys = [
        ForeignKey(
            entity = UserModel::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class MyActivityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val distanceMeters: Int,
    val exerciseStart: String,
    val exerciseEnd: String,
    val comment: String,
    val exerciseType: String
) {
    fun toEntity() = ActivityEntity(
        id = id,
        userId = userId,
        distanceMeters = distanceMeters,
        exerciseStart = DateConvert.stringToDate(exerciseStart),
        exerciseEnd = DateConvert.stringToDate(exerciseEnd),
        comment = comment,
        exerciseType = ExerciseType.valueOf(exerciseType)
    )
}