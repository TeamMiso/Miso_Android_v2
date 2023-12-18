package com.miso.data.repository

import com.miso.data.remote.datasource.purchase.PurchaseDataSource
import com.miso.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val remotePurchaseDatasource: PurchaseDataSource
): PurchaseRepository {
    override suspend fun purchase(id: Long): Flow<Unit> {
        return remotePurchaseDatasource.purchase(id = id)
    }
}