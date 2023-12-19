package com.miso.presentation.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.recyclables.response.AiListModel
import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.domain.model.recyclables.response.ResultResponseModel
import com.miso.domain.model.shop.response.ShopListModel
import com.miso.domain.usecase.recyclables.GetAiListUseCase
import com.miso.presentation.ui.camera.state.CapturedState
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val getAiListUseCase: GetAiListUseCase
) : ViewModel() {

    private val _capturedImgBitmapState = MutableStateFlow(CapturedState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    private val _aiListResponse = MutableStateFlow<Event<AiListResponseModel>>(Event.Loading)
    val aiListResponse = _aiListResponse.asStateFlow()

    var isInquiry = mutableStateOf(false)

    var result = mutableStateOf(
        AiListModel(
            id = 0L,
            title = "", subTitle = "",
            recycleMethod = "",
            recycleTip = "",
            recycleCaution = "",
            imageUrl = "",
            recyclablesType = "",
            recycleMark = ""
        )
    )

    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
        }
    }

    fun getAiList(recyclables: MultipartBody.Part) = viewModelScope.launch {
        getAiListUseCase(
            recyclables = recyclables
        ).onSuccess {
            it.catch { remoteError ->
                _aiListResponse.value = remoteError.errorHandling()
            }.collect {response ->
                _aiListResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _aiListResponse.value = Event.Loading
        }
    }

    fun getMultipartFile(): MultipartBody.Part {
        val fileName = "capturedImage.jpg"
        val mediaType = "image/jpeg"
        val byteArray = swapBitmapToJpegWithMultipartFile().toRequestBody(mediaType.toMediaType())

        return MultipartBody.Part.createFormData("recyclables", fileName, byteArray)
    }

    private fun swapBitmapToJpegWithMultipartFile(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }

    fun swapBitmapToJpeg(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }

    fun setResult(index: Int,aiAnswerList: AiListResponseModel){
        result.value = aiAnswerList.recyclablesList[index]
        Log.d("testt", aiAnswerList.recyclablesList[index].toString())
    }
}