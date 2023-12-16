package com.miso.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel
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
    private val searchableListUseCase: SearchableListUseCase
) : ViewModel() {
    private val _searchResponse = MutableStateFlow<Event<SearchResponseModel>>(Event.Loading)
    val searchResponse = _searchResponse.asStateFlow()

    private val _searchableListResponse = MutableStateFlow<Event<SearchableListResponseModel>>(Event.Loading)
    val searchableListResponse = _searchableListResponse.asStateFlow()

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
}