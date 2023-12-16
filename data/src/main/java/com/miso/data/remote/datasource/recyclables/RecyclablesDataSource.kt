package com.miso.data.remote.datasource.recyclables

import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.SearchableListResponse
import kotlinx.coroutines.flow.Flow

interface RecyclablesDataSource {
    suspend fun search(search: String): Flow<SearchResponse>

    suspend fun searchableList(): Flow<SearchableListResponse>
}