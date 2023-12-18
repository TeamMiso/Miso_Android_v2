package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.user.response.PointResponseModel
import com.miso.domain.model.user.response.UserInfoResponseModel
import com.miso.domain.usecase.user.GetPointUseCase
import com.miso.domain.usecase.user.GetUserInfoUseCase
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getPointUseCase: GetPointUseCase
) : ViewModel() {
    private val _getUserInfoResponse = MutableStateFlow<Event<UserInfoResponseModel>>(Event.Loading)
    val getUserInfoResponse = _getUserInfoResponse.asStateFlow()

    private val _getPointResponse = MutableStateFlow<Event<PointResponseModel>>(Event.Loading)
    val getPointResponse = _getPointResponse.asStateFlow()

    var userInfo = mutableStateOf(
        UserInfoResponseModel(
            id = UUID.randomUUID(),
            email = "",
            password = "",
            point = 0,
            role = ""
        )
    )
        private set

    fun getUserInfo() = viewModelScope.launch {
        getUserInfoUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getUserInfoResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getUserInfoResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getUserInfoResponse.value = it.errorHandling()
            }
    }

    fun getPoint() = viewModelScope.launch {
        getPointUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getPointResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getPointResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getPointResponse.value = error.errorHandling()
            }
    }

    fun saveUserInfo(data: UserInfoResponseModel) {
        userInfo.value = data
    }

    fun savePoint(data: PointResponseModel) {
        userInfo.value.point = data.point
    }
}