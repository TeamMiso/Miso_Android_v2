package com.miso.domain.repository

import com.miso.domain.model.email.request.EmailRequestModel
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    suspend fun email(body: EmailRequestModel): Flow<Unit>
}