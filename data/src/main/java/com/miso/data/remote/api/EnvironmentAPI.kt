package com.miso.data.remote.api

import com.miso.data.remote.dto.environment.response.EnvironmentResponse
import retrofit2.http.GET

interface EnvironmentAPI {
    @GET("environment")
    suspend fun getEnvironment(): EnvironmentResponse
}