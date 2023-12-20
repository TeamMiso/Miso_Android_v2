package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.notification.response.AnswerResponseModel
import com.miso.domain.usecase.notification.GetAnswerUseCase
import com.miso.domain.usecase.notification.SaveDeviceTokenUseCase
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val saveDeviceTokenUseCase: SaveDeviceTokenUseCase
) : ViewModel() {
    private val _getAnswerResponse = MutableStateFlow<Event<AnswerResponseModel>>(Event.Loading)
    val getAnswerResponse = _getAnswerResponse.asStateFlow()

    private val _saveDeviceTokenResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val saveDeviceTokenResponse = _saveDeviceTokenResponse.asStateFlow()

    var answer = mutableStateOf(
        AnswerResponseModel(
            id = 0L,
            answer = ""
        )
    )

    fun getAnswer(id: Long) = viewModelScope.launch {
        getAnswerUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _getAnswerResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getAnswerResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getAnswerResponse.value = it.errorHandling()
            }
    }

    fun saveAnswer(data: AnswerResponseModel) {
        answer.value = data
    }

    fun saveDeviceToken(deviceToken: String) = viewModelScope.launch {
        saveDeviceTokenUseCase(deviceToken = deviceToken)
            .onSuccess {
                it.catch { remoteError ->
                    _saveDeviceTokenResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _saveDeviceTokenResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _saveDeviceTokenResponse.value = it.errorHandling()
            }
    }
}