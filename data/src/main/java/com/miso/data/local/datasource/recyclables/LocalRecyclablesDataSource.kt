package com.miso.data.local.datasource.recyclables

import com.miso.data.remote.dto.recyclables.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface LocalRecyclablesDataSource {
    suspend fun getSearchHistory(): Flow<List<SearchResponse>>

    suspend fun setSearchHistory(searchHistory: SearchResponse)

    suspend fun removeSearchHistory()
}