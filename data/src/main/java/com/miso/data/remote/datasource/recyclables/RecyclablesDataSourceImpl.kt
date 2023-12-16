package com.miso.data.remote.datasource.recyclables

import com.miso.data.remote.api.RecyclablesAPI
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.SearchableListResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecyclablesDataSourceImpl @Inject constructor(
    private val api: RecyclablesAPI
): RecyclablesDataSource {
    override suspend fun search(search: String): Flow<SearchResponse> = flow {
        emit(
            MisoApiHandler<SearchResponse>()
                .httpRequest { api.search(search = search) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun searchableList(): Flow<SearchableListResponse> = flow {
        emit(
            MisoApiHandler<SearchableListResponse>()
                .httpRequest { api.searchableList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}