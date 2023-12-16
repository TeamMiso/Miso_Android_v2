package com.miso.domain.repository

import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import kotlinx.coroutines.flow.Flow

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>

    suspend fun searchableList(): Flow<SearchableListResponseModel>
}