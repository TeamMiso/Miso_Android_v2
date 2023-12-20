package com.miso.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.miso.domain.model.inquiry.response.InquiryListModel
import com.miso.domain.model.inquiry.response.InquiryListResponseModel
import com.miso.domain.usecase.inquiry.GetInquiryListAllUseCase
import com.miso.domain.usecase.inquiry.GetInquiryListDetailUseCase
import com.miso.domain.usecase.inquiry.GetInquiryListUseCase
import com.miso.domain.usecase.inquiry.RequestInquiryUseCase
import com.miso.presentation.ui.inquiry.state.ByteArrayState
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class InquiryViewModel @Inject constructor(
    private val requestInquiryUseCase: RequestInquiryUseCase,
    private val getInquiryListUseCase: GetInquiryListUseCase,
    private val getInquiryListAllUseCase: GetInquiryListAllUseCase,
    private val getInquiryListDetailUseCase: GetInquiryListDetailUseCase,
) : ViewModel() {
    private val _requestInquiryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val requestInquiryResponse = _requestInquiryResponse.asStateFlow()

    private val _getInquiryListResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListResponse = _getInquiryListResponse.asStateFlow()

    private val _getInquiryListAllResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListAllResponse = _getInquiryListAllResponse.asStateFlow()

    private val _getInquiryListDetailResponse = MutableStateFlow<Event<InquiryListDetailResponseModel>>(Event.Loading)
    val getInquiryListDetailResponse = _getInquiryListDetailResponse.asStateFlow()

    var isCamera = mutableStateOf(false)

    var byteArray = mutableStateOf(ByteArrayState())

    var inquiryList = mutableStateListOf<InquiryListModel>()
        private set
    var inquiryListDetail = mutableStateOf(
        InquiryListDetailResponseModel(
            id = 0L,
            inquiryDate = "",
            title = "",
            content = "",
            imageUrl = null,
            inquiryStatus = ""
        )
    )
        private set

    fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody) =
        viewModelScope.launch {
            requestInquiryUseCase(
                filePart = filePart,
                inquiryPart = inquiryPart
            ).onSuccess {
                it.catch { remoteError ->
                    _requestInquiryResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _requestInquiryResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _requestInquiryResponse.value = it.errorHandling()
            }
        }

    fun getInquiryList() = viewModelScope.launch {
        getInquiryListUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListResponse.value = it.errorHandling()
            }
    }

    fun getInquiryListAll() = viewModelScope.launch {
        getInquiryListAllUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListAllResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListAllResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListAllResponse.value = it.errorHandling()
            }
    }

    fun saveInquiryList(data: List<InquiryListModel>) {
        inquiryList.clear()
        inquiryList.addAll(data)
    }

    fun getInquiryListDetail(id: Long) = viewModelScope.launch {
        getInquiryListDetailUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListDetailResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListDetailResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListDetailResponse.value = it.errorHandling()
            }
    }

    fun saveInquiryListDetail(data: InquiryListDetailResponseModel) {
        inquiryListDetail.value = data
    }
}