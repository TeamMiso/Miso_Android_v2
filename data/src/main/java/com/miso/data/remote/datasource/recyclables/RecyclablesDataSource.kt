package com.miso.data.remote.datasource.recyclables

import com.miso.data.remote.dto.recyclables.response.AiListResponse
import com.miso.data.remote.dto.recyclables.response.ResultResponse
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.SearchableListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.http.Multipart

interface RecyclablesDataSource {
    suspend fun search(search: String): Flow<SearchResponse>

    suspend fun searchableList(): Flow<SearchableListResponse>

    suspend fun result(recyclablesType: String): Flow<ResultResponse>

    suspend fun getAiAnswerList(recyclables: MultipartBody.Part): Flow<AiListResponse>
}