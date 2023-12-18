package com.miso.data.remote.datasource.shop

import com.miso.data.remote.dto.shop.response.ShopListResponse
import kotlinx.coroutines.flow.Flow

interface ShopDataSource {
    suspend fun shopList(): Flow<ShopListResponse>
}