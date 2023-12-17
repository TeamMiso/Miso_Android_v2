package com.miso.data.local.datasource.recyclables

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.miso.data.local.key.RecyclablesPreferenceKey
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRecyclablesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalRecyclablesDataSource {
    override suspend fun getSearchHistory(): Flow<List<SearchResponse>> = dataStore.data.map {
        it[RecyclablesPreferenceKey.SEARCH_HISTORY]?.let { searchHistoryJson ->
            val type = object : TypeToken<List<SearchResponse>>() {}.type
            val searchHistoryList = Gson().fromJson<List<SearchResponse>>(searchHistoryJson, type)
            searchHistoryList ?: emptyList()
        } ?: run {
            emptyList()
        }
    }

    override suspend fun setSearchHistory(searchHistory: SearchResponse) {
        dataStore.edit {
            val currentSearchHistory =
                it[RecyclablesPreferenceKey.SEARCH_HISTORY]?.let { searchHistoryJson ->
                    Gson().fromJson(searchHistoryJson, List::class.java) as? MutableList<SearchResponse>
                } ?: mutableListOf()
            currentSearchHistory.add(searchHistory)
            it[RecyclablesPreferenceKey.SEARCH_HISTORY] = Gson().toJson(currentSearchHistory)
        }
    }

    override suspend fun removeSearchHistory() {
        dataStore.edit {
            it.remove(RecyclablesPreferenceKey.SEARCH_HISTORY)
        }
    }
}