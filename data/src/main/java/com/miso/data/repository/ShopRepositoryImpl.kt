package com.miso.data.repository

import com.miso.data.remote.datasource.shop.ShopDataSource
import com.miso.data.remote.dto.shop.response.toShopModel
import com.miso.domain.model.shop.response.ShopListDetailResponseModel
import com.miso.domain.model.shop.response.ShopListResponseModel
import com.miso.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteShopDatasource: ShopDataSource
): ShopRepository {
    override suspend fun shopList(): Flow<ShopListResponseModel> {
        return remoteShopDatasource.shopList().map { it.toShopModel() }
    }

    override suspend fun shopListDetail(id: Long): Flow<ShopListDetailResponseModel> {
        return remoteShopDatasource.shopListDetail(id = id).map { it.toShopModel() }
    }
}