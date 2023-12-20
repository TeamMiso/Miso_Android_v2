package com.miso.data.remote.datasource.notification

import com.miso.data.remote.api.NotificationAPI
import com.miso.data.remote.dto.notification.response.AnswerResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotificationDataSourceImpl @Inject constructor(
    private val api: NotificationAPI
): NotificationDataSource {
    override suspend fun getAnswer(id: Long): Flow<AnswerResponse> = flow {
        emit(
            MisoApiHandler<AnswerResponse>()
                .httpRequest { api.getAnswer(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}