package com.miso.data.repository

import com.miso.data.remote.datasource.purchase.PurchaseDataSource
import com.miso.data.remote.dto.purchase.response.toPurchaseModel
import com.miso.domain.model.purchase.response.PurchaseListResponseModel
import com.miso.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val remotePurchaseDatasource: PurchaseDataSource
): PurchaseRepository {
    override suspend fun purchase(id: Long): Flow<Unit> {
        return remotePurchaseDatasource.purchase(id = id)
    }

    override suspend fun getPurchaseList(): Flow<PurchaseListResponseModel> {
        return remotePurchaseDatasource.getPurchaseList().map { it.toPurchaseModel() }
    }
}