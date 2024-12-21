package com.example.dd_app.data.database.dao

import androidx.room.*
import com.example.dd_app.data.database.model.MyActivityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MyActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyActivity(item: MyActivityModel): Long

    @Query("DELETE FROM my_activities WHERE id = :activityId")
    suspend fun deleteMyActivity(activityId: Int): Int

    @Query("SELECT * FROM my_activities WHERE userId = :userId ORDER BY id DESC")
    fun getAllMyActivities(userId: Int): Flow<List<MyActivityModel>>

    @Query("SELECT * from my_activities WHERE id = :id")
    suspend fun getMyActivityById(id: Int): MyActivityModel?

    @Query("UPDATE my_activities SET comment = :comment WHERE id = :activityId")
    suspend fun updateComment(activityId: Int, comment: String): Int
}