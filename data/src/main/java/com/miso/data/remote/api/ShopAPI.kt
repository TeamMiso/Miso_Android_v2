package com.miso.data.remote.api

import com.miso.data.remote.dto.shop.response.ShopListDetailResponse
import com.miso.data.remote.dto.shop.response.ShopListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopAPI {
    @GET("item")
    suspend fun shopList(): ShopListResponse

    @GET("item/{id}")
    suspend fun shopListDetail(
        @Path("id") id: Long
    ): ShopListDetailResponse
}