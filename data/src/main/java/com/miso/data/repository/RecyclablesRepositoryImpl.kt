package com.miso.data.repository

import android.util.Log
import com.miso.data.local.datasource.recyclables.LocalRecyclablesDataSource
import com.miso.data.remote.datasource.recyclables.RecyclablesDataSource
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.toAiListResponseModel
import com.miso.data.remote.dto.recyclables.response.toResultModel
import com.miso.data.remote.dto.recyclables.response.toSearchModel
import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.domain.model.recyclables.response.ResultResponseModel
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import com.miso.domain.repository.RecyclablesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import javax.inject.Inject

class RecyclablesRepositoryImpl @Inject constructor(
    private val localRecyclablesDataSource: LocalRecyclablesDataSource,
    private val remoteRecyclablesDatasource: RecyclablesDataSource
): RecyclablesRepository {
    override suspend fun search(search: String): Flow<SearchResponseModel> {
        return remoteRecyclablesDatasource.search(search = search).map { it.toSearchModel() }
    }

    override suspend fun searchableList(): Flow<SearchableListResponseModel> {
        return remoteRecyclablesDatasource.searchableList().map { it.toSearchModel() }
    }

    override suspend fun result(recyclablesType: String): Flow<ResultResponseModel> {
        return remoteRecyclablesDatasource.result(recyclablesType = recyclablesType).map { it.toResultModel() }
    }

    override suspend fun saveSearchHistory(searchHistory: SearchResponseModel) {
        searchHistory.let {
            localRecyclablesDataSource.setSearchHistory(
                SearchResponse(
                    title = it.title,
                    imageUrl = it.imageUrl,
                    recycleMethod = it.recycleMethod,
                    recyclablesType = it.recyclablesType
                )
            )
        }
    }

    override suspend fun getSearchHistory(): Flow<List<SearchResponseModel>> {
        return localRecyclablesDataSource.getSearchHistory().map { it.map { it.toSearchModel() } }
    }

    override suspend fun getAiAnswerList(recyclables: MultipartBody.Part): Flow<AiListResponseModel> {
        return remoteRecyclablesDatasource.getAiAnswerList(recyclables = recyclables).map { it.toAiListResponseModel() }
    }
}