package com.miso.data.remote.datasource.recyclables

import com.miso.data.remote.dto.recyclables.response.ResultResponse
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.SearchableListResponse
import kotlinx.coroutines.flow.Flow

interface RecyclablesDataSource {
    suspend fun search(search: String): Flow<SearchResponse>

    suspend fun searchableList(): Flow<SearchableListResponse>

    suspend fun result(recyclablesType: String): Flow<ResultResponse>
}