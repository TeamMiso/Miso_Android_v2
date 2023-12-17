package com.miso.domain.repository

import com.miso.domain.model.recyclables.response.ResultResponseModel
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import kotlinx.coroutines.flow.Flow

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>

    suspend fun searchableList(): Flow<SearchableListResponseModel>

    suspend fun result(recyclablesType: String): Flow<ResultResponseModel>

    suspend fun saveSearchHistory(searchHistory: SearchResponseModel)

    suspend fun getSearchHistory(): Flow<List<SearchResponseModel>>
}