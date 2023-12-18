package com.miso.data.remote.datasource.user

import com.miso.data.remote.dto.user.response.PointResponse
import com.miso.data.remote.dto.user.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow

interface UserDatasource {
    suspend fun getUserInfo(): Flow<UserInfoResponse>

    suspend fun getPoint(): Flow<PointResponse>
}