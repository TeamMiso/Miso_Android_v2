package com.miso.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.purchase.response.PurchaseListModel
import com.miso.domain.model.purchase.response.PurchaseListResponseModel
import com.miso.domain.usecase.purchase.GetPurchaseListUseCase
import com.miso.domain.usecase.purchase.PurchaseUseCase
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchaseUseCase: PurchaseUseCase,
    private val getPurchaseListUseCase: GetPurchaseListUseCase
): ViewModel() {
    private val _purchaseResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val purchaseResponse = _purchaseResponse.asStateFlow()

    private val _getPurchaseListResponse = MutableStateFlow<Event<PurchaseListResponseModel>>(Event.Loading)
    val getPurchaseListResponse = _getPurchaseListResponse.asStateFlow()

    var purchaseList = mutableStateListOf<PurchaseListModel>()
        private set

    fun purchase(id: Long) = viewModelScope.launch {
        purchaseUseCase(id = id)
            .onSuccess {
                it.catch {remoteError ->
                    _purchaseResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _purchaseResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _purchaseResponse.value = it.errorHandling()
            }
    }

    fun initPurchase() {
        _purchaseResponse.value = Event.Loading
    }

    fun getPurchaseList() = viewModelScope.launch {
        getPurchaseListUseCase()
            .onSuccess {
                it.catch {remoteError ->
                    _getPurchaseListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getPurchaseListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getPurchaseListResponse.value = it.errorHandling()
            }
    }

    fun savePurchaseList(data: List<PurchaseListModel>) {
        purchaseList.clear()
        purchaseList.addAll(data)
    }
}