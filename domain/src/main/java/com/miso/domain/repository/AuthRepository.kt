package com.miso.domain.repository

import com.miso.domain.model.auth.request.AuthLogInRequestModel
import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.domain.model.auth.response.AuthLogInResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit>

    suspend fun authLogIn(body: AuthLogInRequestModel): Flow<AuthLogInResponseModel>

    suspend fun saveToken(token: AuthLogInResponseModel)
}