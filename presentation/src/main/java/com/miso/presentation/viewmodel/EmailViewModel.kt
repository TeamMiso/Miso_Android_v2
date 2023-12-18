package com.miso.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import com.miso.domain.model.email.request.EmailRequestModel
import com.miso.domain.usecase.email.EmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val emailUseCase: EmailUseCase
): ViewModel() {

    private val _emailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val emailResponse = _emailResponse.asStateFlow()

    fun email(body: EmailRequestModel) = viewModelScope.launch {
        emailUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _emailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _emailResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _emailResponse.value = it.errorHandling()
        }
    }
}