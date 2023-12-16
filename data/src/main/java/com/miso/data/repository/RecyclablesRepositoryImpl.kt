package com.miso.data.repository

import com.miso.data.remote.datasource.recyclables.RecyclablesDataSource
import com.miso.data.remote.dto.recyclables.response.toSearchModel
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import com.miso.domain.repository.RecyclablesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecyclablesRepositoryImpl @Inject constructor(
    private val remoteRecyclablesDatasource: RecyclablesDataSource
): RecyclablesRepository {
    override suspend fun search(search: String): Flow<SearchResponseModel> {
        return remoteRecyclablesDatasource.search(search = search).map { it.toSearchModel() }
    }

    override suspend fun searchableList(): Flow<SearchableListResponseModel> {
        return remoteRecyclablesDatasource.searchableList().map { it.toSearchModel() }
    }
}