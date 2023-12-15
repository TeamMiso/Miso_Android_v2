package com.miso.data.remote.datasource.recyclables

import com.miso.data.remote.dto.recyclables.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface RecyclablesDataSource {
    suspend fun search(search: String): Flow<SearchResponse>
}