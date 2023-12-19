package com.miso.data.remote.api

import com.miso.data.remote.dto.purchase.response.PurchaseListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PurchaseAPI {
    @POST("purchase/{id}")
    suspend fun purchase(
        @Path("id") id: Long
    )

    @GET("purchase")
    suspend fun getPurchaseList(): PurchaseListResponse
}