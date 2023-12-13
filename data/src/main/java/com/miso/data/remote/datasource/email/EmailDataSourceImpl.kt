package com.miso.data.remote.datasource.email

import com.miso.data.remote.api.EmailAPI
import com.miso.data.remote.dto.email.request.EmailRequest
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EmailDataSourceImpl @Inject constructor(
    private val api: EmailAPI
): EmailDataSource {
    override suspend fun email(body: EmailRequest): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.email(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}