package com.miso.domain.repository

import com.miso.domain.model.notification.response.AnswerResponseModel
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun getAnswer(id: Long): Flow<AnswerResponseModel>
}