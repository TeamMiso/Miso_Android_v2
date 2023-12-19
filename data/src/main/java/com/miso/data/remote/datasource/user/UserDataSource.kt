package com.miso.data.remote.datasource.user

import com.miso.data.remote.dto.user.response.PointResponse
import com.miso.data.remote.dto.user.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUserInfo(): Flow<UserInfoResponse>

    suspend fun getPoint(): Flow<PointResponse>

    suspend fun givePoint(): Flow<Unit>
}