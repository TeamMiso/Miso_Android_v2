package com.miso.data.remote.datasource.purchase

import com.miso.data.remote.dto.purchase.response.PurchaseListResponse
import kotlinx.coroutines.flow.Flow

interface PurchaseDataSource {
    suspend fun purchase(id: Long): Flow<Unit>

    suspend fun getPurchaseList(): Flow<PurchaseListResponse>
}