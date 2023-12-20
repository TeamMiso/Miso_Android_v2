package com.miso.data.repository

import com.miso.data.remote.datasource.notification.NotificationDataSource
import com.miso.data.remote.dto.notification.response.toNotificationModel
import com.miso.domain.model.notification.response.AnswerResponseModel
import com.miso.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val remoteNotificationDataSource: NotificationDataSource
): NotificationRepository {
    override suspend fun getAnswer(id: Long): Flow<AnswerResponseModel> {
        return remoteNotificationDataSource.getAnswer(id = id).map { it.toNotificationModel() }
    }
}