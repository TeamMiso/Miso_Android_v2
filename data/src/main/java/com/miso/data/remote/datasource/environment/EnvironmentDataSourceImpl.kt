package com.miso.data.remote.datasource.environment

import com.miso.data.remote.api.EnvironmentAPI
import com.miso.data.remote.dto.environment.response.EnvironmentResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EnvironmentDataSourceImpl @Inject constructor(
    private val api: EnvironmentAPI
): EnvironmentDataSource {
    override suspend fun getEnvironment(): Flow<EnvironmentResponse> = flow {
        emit(
            MisoApiHandler<EnvironmentResponse>()
                .httpRequest { api.getEnvironment() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}