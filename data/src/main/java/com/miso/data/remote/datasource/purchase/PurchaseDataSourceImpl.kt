package com.miso.data.remote.datasource.purchase

import com.miso.data.remote.api.PurchaseAPI
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PurchaseDataSourceImpl @Inject constructor(
    private val api: PurchaseAPI
): PurchaseDataSource {
    override suspend fun purchase(id: Long): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.purchase(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}