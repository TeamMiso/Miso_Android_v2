package com.miso.data.repository

import com.miso.data.remote.datasource.auth.AuthDataSource
import com.miso.data.remote.dto.auth.request.AuthLogInRequest
import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import com.miso.data.remote.dto.auth.response.toLogInModel
import com.miso.domain.model.auth.request.AuthLogInRequestModel
import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.domain.model.auth.response.AuthLogInResponseModel
import com.miso.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun authLogIn(body: AuthLogInRequestModel): Flow<AuthLogInResponseModel> {
        return remoteAuthDatasource.authLogIn(
            body = AuthLogInRequest(
                email = body.email,
                password = body.password,
            )
        ).map { it.toLogInModel() }
    }
}