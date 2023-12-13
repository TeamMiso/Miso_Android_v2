package com.miso.domain.repository

import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit>
}