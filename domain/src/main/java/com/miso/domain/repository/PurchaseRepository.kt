package com.miso.domain.repository

import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun purchase(id: Long): Flow<Unit>
}