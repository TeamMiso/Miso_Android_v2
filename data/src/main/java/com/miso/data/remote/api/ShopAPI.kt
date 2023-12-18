package com.miso.data.remote.api

import com.miso.data.remote.dto.shop.response.ShopListResponse
import retrofit2.http.GET

interface ShopAPI {
    @GET("item")
    suspend fun shopList(): ShopListResponse
}