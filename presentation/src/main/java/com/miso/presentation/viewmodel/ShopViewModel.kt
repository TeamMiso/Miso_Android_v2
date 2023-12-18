package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import com.miso.domain.model.shop.response.ShopListModel
import com.miso.domain.model.shop.response.ShopListResponseModel
import com.miso.domain.usecase.shop.ShopListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopListUseCase: ShopListUseCase,
) : ViewModel() {
    private val _shopListResponse = MutableStateFlow<Event<ShopListResponseModel>>(Event.Loading)
    val shopListResponse = _shopListResponse.asStateFlow()

    var shopList = mutableStateListOf<ShopListModel>()
        private set

    fun shopList() = viewModelScope.launch {
        shopListUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _shopListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _shopListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _shopListResponse.value = it.errorHandling()
            }
    }

    fun saveShopList(data: List<ShopListModel>) {
        shopList.clear()
        shopList.addAll(data)
    }
}