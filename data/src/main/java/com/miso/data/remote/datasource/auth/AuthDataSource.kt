package com.miso.data.remote.datasource.auth

import com.miso.data.remote.dto.auth.request.AuthLogInRequest
import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import com.miso.data.remote.dto.auth.response.AuthLogInResponse
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit>

    suspend fun authLogIn(body: AuthLogInRequest): Flow<AuthLogInResponse>
}