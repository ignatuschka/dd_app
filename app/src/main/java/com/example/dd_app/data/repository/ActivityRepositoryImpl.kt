package com.example.dd_app.data.repository

import com.example.dd_app.data.data_source.local.ActivityLocalDataSource
import com.example.dd_app.data.data_source.remote.ActivityRemoteDataSource
import com.example.dd_app.domain.entity.MyActivityEntity
import com.example.dd_app.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val local: ActivityLocalDataSource,
    private val remote: ActivityRemoteDataSource
) :
    ActivityRepository {
    override fun getAllMyActivities(userId: Int): Flow<List<MyActivityEntity>> =
        local.getAllMyActivities(userId)
            .map { list -> list.map { element -> element.toEntity() } }

    override suspend fun insertMyActivity(item: MyActivityEntity) =
        local.insertMyActivity(item.toModel()) != -1L

    override suspend fun deleteMyActivity(activityId: Int) =
        local.deleteMyActivity(activityId) > 0

    override suspend fun getMyActivityById(id: Int): MyActivityEntity? =
        local.getMyActivityById(id)?.toEntity()

    override suspend fun updateComment(activityId: Int, comment: String): Boolean =
        local.updateComment(activityId, comment) > 0

    override suspend fun getAllUserActivities(): List<MyActivityEntity> =
        remote.getAllUserActivities().map { it.toEntity() }

    override suspend fun getUserActivity(activityId: Int): MyActivityEntity =
        remote.getUserActivity(activityId).toEntity()
}