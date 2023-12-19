package com.miso.data.remote.datasource.auth

import com.miso.data.remote.api.AuthAPI
import com.miso.data.remote.dto.auth.request.AuthLogInRequest
import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import com.miso.data.remote.dto.auth.response.AuthLogInResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthAPI
): AuthDataSource {
    override suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.authSignUp(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun authLogIn(body: AuthLogInRequest): Flow<AuthLogInResponse> = flow {
        emit(
            MisoApiHandler<AuthLogInResponse>()
                .httpRequest { api.authLogIn(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun logout(): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.logout() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}