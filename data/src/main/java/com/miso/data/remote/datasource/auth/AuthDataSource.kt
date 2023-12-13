package com.miso.data.remote.datasource.auth

import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit>
}