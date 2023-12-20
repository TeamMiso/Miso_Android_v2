package com.miso.data.remote.api

import com.miso.data.remote.dto.notification.response.AnswerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NotificationAPI {
    @GET("notification/{id}")
    suspend fun getAnswer(
        @Path("id") id: Long
    ): AnswerResponse
}