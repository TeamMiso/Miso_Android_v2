package com.miso.data.remote.datasource.user

import com.miso.data.remote.api.UserAPI
import com.miso.data.remote.dto.user.response.PointResponse
import com.miso.data.remote.dto.user.response.UserInfoResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val api: UserAPI
): UserDataSource {
    override suspend fun getUserInfo(): Flow<UserInfoResponse> = flow {
        emit(
            MisoApiHandler<UserInfoResponse>()
                .httpRequest { api.getUserInfo() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getPoint(): Flow<PointResponse> = flow {
        emit(
            MisoApiHandler<PointResponse>()
                .httpRequest { api.getPoint() }
                .sendRequest()
        )
    }

    override suspend fun givePoint(): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.givePoint() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}