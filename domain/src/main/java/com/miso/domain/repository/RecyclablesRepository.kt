package com.miso.domain.repository

import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.domain.model.recyclables.response.ResultResponseModel
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>

    suspend fun searchableList(): Flow<SearchableListResponseModel>

    suspend fun result(recyclablesType: String): Flow<ResultResponseModel>

    suspend fun saveSearchHistory(searchHistory: SearchResponseModel)

    suspend fun getSearchHistory(): Flow<List<SearchResponseModel>>

    suspend fun deleteSearchHistory()

    suspend fun getAiAnswerList(recyclables: MultipartBody.Part): Flow<AiListResponseModel>
}