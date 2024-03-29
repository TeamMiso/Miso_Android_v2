package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.shop.response.ShopListDetailResponseModel
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import com.miso.domain.model.shop.response.ShopListModel
import com.miso.domain.model.shop.response.ShopListResponseModel
import com.miso.domain.usecase.shop.ShopListDetailUseCase
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
    private val shopListDetailUseCase: ShopListDetailUseCase
) : ViewModel() {
    private val _shopListResponse = MutableStateFlow<Event<ShopListResponseModel>>(Event.Loading)
    val shopListResponse = _shopListResponse.asStateFlow()

    private val _shopListDetailResponse = MutableStateFlow<Event<ShopListDetailResponseModel>>(Event.Loading)
    val shopListDetailResponse = _shopListDetailResponse.asStateFlow()

    var shopList = mutableStateListOf<ShopListModel>()
        private set
    var shopListDetail = mutableStateOf(
        ShopListDetailResponseModel(
            id = 0L,
            price = 0,
            amount = 0,
            name = "",
            content = "",
            imageUrl = ""
        )
    )
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

    fun shopListDetail(id: Long) = viewModelScope.launch {
        _shopListDetailResponse.value = Event.Loading
        shopListDetailUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _shopListDetailResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _shopListDetailResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _shopListDetailResponse.value = it.errorHandling()
            }
    }

    fun saveShopListDetail(data: ShopListDetailResponseModel) {
        shopListDetail.value = data
    }
}