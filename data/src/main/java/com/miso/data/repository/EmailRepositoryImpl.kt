package com.miso.data.repository

import com.miso.data.remote.datasource.email.EmailDataSource
import com.miso.data.remote.dto.email.request.EmailRequest
import com.miso.domain.model.email.request.EmailRequestModel
import com.miso.domain.repository.EmailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val remoteEmailDatasource: EmailDataSource
): EmailRepository {
    override suspend fun email(body: EmailRequestModel): Flow<Unit> {
        return remoteEmailDatasource.email(
            body = EmailRequest(
                randomKey = body.randomKey
            )
        )
    }
}