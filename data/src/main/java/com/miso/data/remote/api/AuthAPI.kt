package com.miso.data.remote.api

import com.miso.data.remote.dto.auth.request.AuthSignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth")
    suspend fun authSignUp(
        @Body body: AuthSignUpRequest
    )
}