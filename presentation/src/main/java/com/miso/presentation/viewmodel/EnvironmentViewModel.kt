package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.environment.response.EnvironmentResponseModel
import com.miso.domain.usecase.environment.GetEnvironmentUseCase
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnvironmentViewModel @Inject constructor(
    private val getEnvironmentUseCase: GetEnvironmentUseCase
) : ViewModel() {
    private val _getEnvironmentResponse = MutableStateFlow<Event<EnvironmentResponseModel>>(Event.Loading)
    val getEnvironmentResponse = _getEnvironmentResponse.asStateFlow()

    var environment = mutableStateOf(
        EnvironmentResponseModel(
            title = "",
            content = "",
            imageUrl = ""
        )
    )
        private set

    fun getEnvironment() = viewModelScope.launch {
        getEnvironmentUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getEnvironmentResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getEnvironmentResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getEnvironmentResponse.value = it.errorHandling()
            }
    }

    fun saveEnvironment(data: EnvironmentResponseModel) {
        environment.value = data
    }
}