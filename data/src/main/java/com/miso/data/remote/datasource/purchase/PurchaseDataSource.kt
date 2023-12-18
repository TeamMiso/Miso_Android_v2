package com.miso.data.remote.datasource.purchase

import kotlinx.coroutines.flow.Flow

interface PurchaseDataSource {
    suspend fun purchase(id: Long): Flow<Unit>
}