package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import com.miso.domain.model.recyclables.response.ResultResponseModel
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
import com.miso.domain.usecase.recyclables.GetSearchHistoryUseCase
import com.miso.domain.usecase.recyclables.ResultUseCase
import com.miso.domain.usecase.recyclables.SaveSearchHistoryUseCase
import com.miso.domain.usecase.recyclables.SearchUseCase
import com.miso.domain.usecase.recyclables.SearchableListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclablesViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val searchableListUseCase: SearchableListUseCase,
    private val resultUseCase: ResultUseCase,
    private val saveSearchHistoryUseCase: SaveSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : ViewModel() {
    private val _searchResponse = MutableStateFlow<Event<SearchResponseModel>>(Event.Loading)
    val searchResponse = _searchResponse.asStateFlow()

    private val _searchableListResponse = MutableStateFlow<Event<SearchableListResponseModel>>(Event.Loading)
    val searchableListResponse = _searchableListResponse.asStateFlow()

    private val _resultResponse = MutableStateFlow<Event<ResultResponseModel>>(Event.Loading)
    val resultResponse = _resultResponse.asStateFlow()

    private val _saveSearchHistoryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val saveSearchHistoryResponse = _saveSearchHistoryResponse.asStateFlow()

    private val _getSearchHistoryResponse = MutableStateFlow<Event<List<SearchResponseModel>>>(Event.Loading)
    val getSearchHistoryResponse = _getSearchHistoryResponse.asStateFlow()

    var search = mutableStateOf(
        SearchResponseModel(
            title = "",
            imageUrl = "",
            recycleMethod = "",
            recyclablesType = ""
        )
    )
        private set
    var recyclableList = mutableStateListOf<SearchableListModel>()
        private set
    var result = mutableStateOf(
        ResultResponseModel(
            id = 0L,
            title = "", subTitle = "",
            recycleMethod = "",
            recycleTip = "",
            recycleCaution = "",
            imageUrl = "",
            recyclablesType = "",
            recycleMark = ""
        )
    )
        private set
    var searchHistory = mutableStateListOf<SearchResponseModel>()
        private set

    fun search(search: String) = viewModelScope.launch {
        searchUseCase(search = search)
            .onSuccess {
                it.catch { remoteError ->
                    _searchResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _searchResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _searchResponse.value = it.errorHandling()
            }
    }

    fun saveSearch(data: SearchResponseModel) {
        search.value = data
    }

    fun searchableList() = viewModelScope.launch {
        searchableListUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _searchableListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _searchableListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _searchableListResponse.value = it.errorHandling()
            }
    }

    fun saveSearchableList(data: List<SearchableListModel>) {
        recyclableList.clear()
        recyclableList.addAll(data)
    }

    fun result(recyclablesType: String) = viewModelScope.launch {
        _resultResponse.value = Event.Loading
        resultUseCase(recyclablesType = recyclablesType)
            .onSuccess {
                it.catch { remoteError ->
                    _resultResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _resultResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _resultResponse.value = it.errorHandling()
            }
    }

    fun saveResult(data: ResultResponseModel) {
        result.value = data
    }

    fun saveSearchHistory(searchHistory: SearchResponseModel) = viewModelScope.launch {
        saveSearchHistoryUseCase(
            searchHistory = searchHistory
        ).onSuccess {
            _saveSearchHistoryResponse.value = Event.Success()
        }.onFailure {
            _saveSearchHistoryResponse.value = it.errorHandling()
        }
    }

    fun getSearchHistory() = viewModelScope.launch {
        getSearchHistoryUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getSearchHistoryResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getSearchHistoryResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getSearchHistoryResponse.value = it.errorHandling()
            }
    }

    fun saveSearchHistory(data: List<SearchResponseModel>) {
        searchHistory.clear()
        searchHistory.addAll(data)
    }
}