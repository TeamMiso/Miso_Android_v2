package com.miso.domain.repository

import com.miso.domain.model.recyclables.response.SearchResponseModel
import kotlinx.coroutines.flow.Flow

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>
}