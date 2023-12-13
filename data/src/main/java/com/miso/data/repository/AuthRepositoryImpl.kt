package com.miso.data.repository

import com.miso.data.remote.datasource.auth.AuthDataSource
import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDatasource: AuthDataSource
): AuthRepository {
    override suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit> {
        return remoteAuthDatasource.authSignUp(
            body = AuthSignUpRequest(
                email = body.email,
                password = body.password,
                passwordCheck = body.passwordCheck
            )
        )
    }
}