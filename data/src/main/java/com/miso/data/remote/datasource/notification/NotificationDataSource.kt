package com.miso.data.remote.datasource.notification

import com.miso.data.remote.dto.notification.response.AnswerResponse
import kotlinx.coroutines.flow.Flow

interface NotificationDataSource {
    suspend fun getAnswer(id: Long): Flow<AnswerResponse>

    suspend fun saveDeviceToken(deviceToken: String): Flow<Unit>
}