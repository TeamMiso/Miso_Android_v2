package com.miso.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val purchaseUseCase: PurchaseUseCase
): ViewModel() {
    private val _purchaseResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val purchaseResponse = _purchaseResponse.asStateFlow()

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
}