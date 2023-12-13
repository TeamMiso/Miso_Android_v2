package com.miso.data.remote.datasource.email

import com.miso.data.remote.dto.email.request.EmailRequest
import kotlinx.coroutines.flow.Flow

interface EmailDataSource {
    suspend fun email(body: EmailRequest): Flow<Unit>
}