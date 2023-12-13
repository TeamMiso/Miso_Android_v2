package com.miso.data.remote.api

import com.miso.data.remote.dto.email.request.EmailRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailAPI {
    @POST("email")
    suspend fun email(
        @Body body: EmailRequest
    )
}