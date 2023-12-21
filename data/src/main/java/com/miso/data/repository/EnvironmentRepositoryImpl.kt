package com.miso.data.repository

import com.miso.data.remote.datasource.environment.EnvironmentDataSource
import com.miso.data.remote.dto.environment.response.toEnvironmentModel
import com.miso.domain.model.environment.response.EnvironmentResponseModel
import com.miso.domain.repository.EnvironmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EnvironmentRepositoryImpl @Inject constructor(
    private val remoteEnvironmentDataSource: EnvironmentDataSource
): EnvironmentRepository {
    override suspend fun getEnvironment(): Flow<EnvironmentResponseModel> {
        return remoteEnvironmentDataSource.getEnvironment().map { it.toEnvironmentModel() }
    }
}