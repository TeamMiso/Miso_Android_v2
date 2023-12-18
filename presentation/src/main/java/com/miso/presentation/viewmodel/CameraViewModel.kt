package com.miso.presentation.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import com.miso.domain.model.recyclables.request.AiRequestModel
import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.domain.usecase.recyclables.GetAiListUseCase
import com.miso.presentation.ui.camera.state.CapturedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.http.RealResponseBody
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
                Log.d("testt",remoteError.toString())
            }.collect {response ->
                _aiListResponse.value = Event.Success(data = response)
                Log.d("testt",response.toString())
            }
        }.onFailure {
            _aiListResponse.value = Event.Loading
        }
    }

    fun getMultipartFile(): MultipartBody.Part {
        val fileName = "capturedImage.jpg"
        val mediaType = "image/jpeg"
        val byteArray = swapBitmapToJpeg().toRequestBody(mediaType.toMediaType())

        Log.d("testt",MultipartBody.Part.createFormData("file", fileName, byteArray).toString())
        return MultipartBody.Part.createFormData("file", fileName, byteArray)
    }

    private fun swapBitmapToJpeg(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }
}