package com.miso.data.repository

import com.miso.data.remote.datasource.user.UserDataSource
import com.miso.data.remote.dto.user.response.toUserModel
import com.miso.domain.model.user.response.PointResponseModel
import com.miso.domain.model.user.response.UserInfoResponseModel
import com.miso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDatasource: UserDataSource
): UserRepository {
    override suspend fun getUserInfo(): Flow<UserInfoResponseModel> {
        return remoteUserDatasource.getUserInfo().map { it.toUserModel() }
    }

    override suspend fun getPoint(): Flow<PointResponseModel> {
        return remoteUserDatasource.getPoint().map { it.toUserModel() }
    }
}