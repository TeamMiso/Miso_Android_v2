package com.miso.domain.repository

import com.miso.domain.model.purchase.response.PurchaseListResponseModel
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun purchase(id: Long): Flow<Unit>

    suspend fun getPurchaseList(): Flow<PurchaseListResponseModel>
}