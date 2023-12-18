package com.miso.domain.repository

import com.miso.domain.model.user.response.PointResponseModel
import com.miso.domain.model.user.response.UserInfoResponseModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserInfo(): Flow<UserInfoResponseModel>

    suspend fun getPoint(): Flow<PointResponseModel>
}