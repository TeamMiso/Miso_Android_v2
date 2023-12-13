package com.miso.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import com.miso.domain.model.auth.request.AuthLogInRequestModel
import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.domain.model.auth.response.AuthLogInResponseModel
import com.miso.domain.usecase.auth.AuthLogInUseCase
import com.miso.domain.usecase.auth.AuthSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authSignUpUseCase: AuthSignUpUseCase,
    private val authLogInUseCase: AuthLogInUseCase,
) : ViewModel() {

    private val _authSignUpResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val authSignUpResponse = _authSignUpResponse.asStateFlow()

    private val _authLogInResponse = MutableStateFlow<Event<AuthLogInResponseModel>>(Event.Loading)
    val authLogInResponse = _authLogInResponse.asStateFlow()

    fun authSignUp(body: AuthSignUpRequestModel) = viewModelScope.launch {
        authSignUpUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _authSignUpResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _authSignUpResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _authSignUpResponse.value = it.errorHandling()
        }
    }

    fun authLogIn(body: AuthLogInRequestModel) = viewModelScope.launch {
        authLogInUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _authLogInResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _authLogInResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _authLogInResponse.value = it.errorHandling()
        }
    }
}