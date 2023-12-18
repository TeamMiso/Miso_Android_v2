package com.miso.data.remote.api

import com.miso.data.remote.dto.user.response.PointResponse
import com.miso.data.remote.dto.user.response.UserInfoResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("user")
    suspend fun getUserInfo(): UserInfoResponse

    @GET("user/point")
    suspend fun getPoint(): PointResponse
}