package com.miso.data.remote.datasource.shop

import com.miso.data.remote.api.ShopAPI
import com.miso.data.remote.dto.shop.response.ShopListDetailResponse
import com.miso.data.remote.dto.shop.response.ShopListResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ShopDataSourceImpl @Inject constructor(
    private val api: ShopAPI
): ShopDataSource{
    override suspend fun shopList(): Flow<ShopListResponse> = flow {
        emit(
            MisoApiHandler<ShopListResponse>()
                .httpRequest { api.shopList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun shopListDetail(id: Long): Flow<ShopListDetailResponse> = flow {
        emit(
            MisoApiHandler<ShopListDetailResponse>()
                .httpRequest { api.shopListDetail(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}