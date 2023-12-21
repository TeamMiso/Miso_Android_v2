package com.miso.data.remote.datasource.environment

import com.miso.data.remote.dto.environment.response.EnvironmentResponse
import kotlinx.coroutines.flow.Flow

interface EnvironmentDataSource {
    suspend fun getEnvironment(): Flow<EnvironmentResponse>
}