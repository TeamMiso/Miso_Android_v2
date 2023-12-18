package com.miso.data.remote.api

import retrofit2.http.POST
import retrofit2.http.Path

interface PurchaseAPI {
    @POST("purchase/{id}")
    suspend fun purchase(
        @Path("id") id: Long
    )
}