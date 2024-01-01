package com.miso.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.domain.model.recyclables.response.AiListModel
import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.domain.usecase.recyclables.GetAiListUseCase
import com.miso.presentation.R
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

    private val _defaultImageBitmap = MutableStateFlow(CapturedState())
    val defaultImageBitmap = _defaultImageBitmap.asStateFlow()

    private val _aiListResponse = MutableStateFlow<Event<AiListResponseModel>>(Event.Loading)
    val aiListResponse = _aiListResponse.asStateFlow()

    var isInquiry = mutableStateOf(false)

    var isInconsistency = mutableStateOf(false)

    var aiList = mutableStateListOf<AiListModel>()
        private set

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

    fun getBitmap(): ImageBitmap? {
        (captureImgBitmapState.value.capturedImage?.asImageBitmap() ?: null)?.let {
            return it
        }
        return null
    }

    fun getMultipartFile(context: Context,isDefault: Boolean): MultipartBody.Part {
        val fileName = "capturedImage.jpg"
        val mediaType = "image/jpeg"
        val byteArray = if(isDefault) {
            _defaultImageBitmap.value.capturedImage?.recycle()
            _defaultImageBitmap.value = _defaultImageBitmap.value.copy(
                capturedImage = getBitmapFromDrawableResourceId(
                    context,
                    com.miso.design_system.R.drawable.ic_miso_color
                )
            )
            swapBitmapToJpegWithMultipartFile(true).toRequestBody(mediaType.toMediaType())
        } else {
            swapBitmapToJpegWithMultipartFile(false).toRequestBody(mediaType.toMediaType())
        }

        return MultipartBody.Part.createFormData("recyclables", fileName, byteArray)
    }

    private fun getBitmapFromDrawableResourceId(context: Context, drawableResId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableResId)
            ?: throw IllegalArgumentException("Invalid drawable resource ID")

        return drawableToBitmap(drawable)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    private fun swapBitmapToJpegWithMultipartFile(isDefault: Boolean): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = if(isDefault) {
            _defaultImageBitmap.value.capturedImage
        } else {
            _capturedImgBitmapState.value.capturedImage
        }

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }

    fun swapBitmapToJpeg(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }

    fun saveAiList(data: List<AiListModel>) {
        aiList.clear()
        aiList.addAll(data)
    }

    fun setResult(index: Int){
        result.value = aiList[index]
    }
}