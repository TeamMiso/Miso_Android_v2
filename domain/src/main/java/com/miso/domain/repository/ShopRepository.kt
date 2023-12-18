package com.miso.domain.repository

import com.miso.domain.model.shop.response.ShopListResponseModel
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun shopList(): Flow<ShopListResponseModel>
}