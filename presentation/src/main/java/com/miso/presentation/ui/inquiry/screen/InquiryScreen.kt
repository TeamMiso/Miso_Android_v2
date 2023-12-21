package com.miso.presentation.ui.inquiry.screen

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.miso.design_system.theme.MisoTheme
import com.miso.domain.model.inquiry.request.InquiryRequestModel
import com.miso.presentation.ui.inquiry.component.InquiryContentTextField
import com.miso.presentation.ui.inquiry.component.InquiryImageButton
import com.miso.presentation.ui.inquiry.component.InquiryTitleTextField
import com.miso.presentation.ui.inquiry.component.InquiryTopBar
import com.miso.presentation.ui.inquiry.component.bottomsheet.SelectPhotoPathBottomSheet
import com.miso.presentation.ui.inquiry.util.getMultipartFile
import com.miso.presentation.ui.inquiry.util.toMultipartBody
import com.miso.presentation.ui.search.MainPage
import com.miso.presentation.ui.search.SubPage
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.InquiryViewModel
import com.miso.presentation.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InquiryScreen(
    context: Context,
    onCameraClick: () -> Unit,
    onInquiryClick: (filePart: MultipartBody.Part?, inquiryPart: RequestBody) -> Unit,
    viewModel: InquiryViewModel,
    cameraViewModel: CameraViewModel,
    lifecycleScope: CoroutineScope,
    navController: NavController
) {
    var bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val bottomSheetScope = rememberCoroutineScope()

    val progressState = remember { mutableStateOf(false) }

    var isImageEmpty by remember { mutableStateOf(true) }

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf(Uri.EMPTY) }

    val filePart = if (imageUri != Uri.EMPTY) {
        if(isImageEmpty) {
            cameraViewModel.getMultipartFile(context, true)
        } else {
            imageUri.toMultipartBody(context)
        }
    } else {
        null
    }

    val inquiryData = InquiryRequestModel(
        title = title,
        content = content
    )

    val inquiryJson = Gson().toJson(inquiryData)

    val inquiryRequestBody = inquiryJson.toRequestBody("application/json".toMediaType())

    ModalBottomSheetLayout(
        sheetContent = {
            SelectPhotoPathBottomSheet(
                bottomSheetState = bottomSheetState,
                selectedImageUri = { uri ->
                    isImageEmpty = false
                    imageUri = uri
                },
                onCameraClick = {
                    onCameraClick()
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState
    ) {
        MisoTheme { colors, typography ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.WHITE)
                    .statusBarsPadding()
            ) {
                InquiryTopBar(
                    onInquiryClick = {
                        if (title.isNotEmpty() && content.isNotEmpty()) {
                            lifecycleScope.launch {
                                inquiry(
                                    viewModel = viewModel,
                                    navController = navController,
                                    errorText = {},
                                    progressState = { state ->
                                        progressState.value = state
                                    }
                                )
                            }
                            if (!isImageEmpty) {
                                if (viewModel.isCamera.value) {
                                    onInquiryClick(getMultipartFile(viewModel.byteArray.value.byteArray!!), inquiryRequestBody)
                                } else {
                                    onInquiryClick(filePart, inquiryRequestBody)
                                }
                            } else {
                                onInquiryClick(filePart, inquiryRequestBody)
                            }
                        }
                    },
                    onBackClick = {
                        viewModel.isCamera.value = false
                        navController.navigate(MainPage.Search.value){
                            popUpTo(MainPage.Search.value){
                                inclusive = true
                            }
                        }
                    },
                    isUser = true
                )
                InquiryTitleTextField(
                    title = title,
                    onValueChange = {
                        title = it
                    }
                )
                InquiryImageButton(
                    onClick = {
                        bottomSheetScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    selectedImageUri = imageUri,
                    capturedImage = if(viewModel.isCamera.value) {
                        isImageEmpty = false
                        getBitmap(viewModel)
                    } else {
                        isImageEmpty = true
                        null
                    }
                )
                InquiryContentTextField(
                    content = content,
                    onValueChange = {
                        content = it
                    }
                )
            }
        }
    }
}

suspend fun inquiry(
    viewModel: InquiryViewModel,
    navController: NavController,
    errorText: (errorText: String) -> Unit,
    progressState: (progressState: Boolean) -> Unit
) {
    viewModel.requestInquiryResponse.collect {
        when (it) {
            is Event.Loading -> {
                progressState(true)
            }

            is Event.Success -> {
                viewModel.isCamera.value = false
                navController.navigate(MainPage.Search.value){
                    popUpTo(MainPage.Search.value){
                        inclusive = true
                    }
                }
                progressState(false)
            }

            else -> {
                errorText("알 수 없는 에러가 발생했습니다!")
                progressState(false)
            }
        }
    }
}

@Composable
private fun getBitmap(viewModel: InquiryViewModel): ImageBitmap? {
    val bitmap = BitmapFactory.decodeByteArray(
        viewModel.byteArray.value.byteArray,
        0,
        viewModel.byteArray.value.byteArray?.
        size ?: 0
    )
    (bitmap.asImageBitmap() ?: null)?.let {
        return it
    }
    return null
}

