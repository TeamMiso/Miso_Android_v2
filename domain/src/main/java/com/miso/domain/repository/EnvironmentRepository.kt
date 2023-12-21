package com.miso.domain.repository

import com.miso.domain.model.environment.response.EnvironmentResponseModel
import kotlinx.coroutines.flow.Flow

interface EnvironmentRepository {
    suspend fun getEnvironment(): Flow<EnvironmentResponseModel>
}