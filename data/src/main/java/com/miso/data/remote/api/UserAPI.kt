package com.miso.data.remote.api

import com.miso.data.remote.dto.user.response.PointResponse
import com.miso.data.remote.dto.user.response.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {
    @GET("user")
    suspend fun getUserInfo(): UserInfoResponse

    @POST("user/point")
    suspend fun getPoint(): PointResponse
}