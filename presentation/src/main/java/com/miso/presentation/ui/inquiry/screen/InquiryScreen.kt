package com.miso.presentation.ui.inquiry.screen

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryImageButton
import com.miso.presentation.ui.inquiry.component.InquiryTextTextField
import com.miso.presentation.ui.inquiry.component.InquiryTitleTextField
import com.miso.presentation.ui.inquiry.component.InquiryTopBar
import com.miso.presentation.ui.inquiry.component.bottomsheet.SelectPhotoPathBottomSheet
import com.miso.presentation.ui.inquiry.util.getMultipartFile
import com.miso.presentation.ui.inquiry.util.toMultipartBody
import com.miso.presentation.ui.search.MainPage
import com.miso.presentation.ui.search.SearchActivity
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.InquiryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InquiryScreen(
    context: Context,
    onCameraClick: () -> Unit,
    viewModel: InquiryViewModel,
    navController: NavController
) {
    var bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val bottomSheetScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf(Uri.EMPTY) }

    val filePart = if (imageUri != Uri.EMPTY) {
        imageUri.toMultipartBody(context)
    } else null

    ModalBottomSheetLayout(
        sheetContent = {
            SelectPhotoPathBottomSheet(
                bottomSheetState = bottomSheetState,
                selectedImageUri = { uri ->
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
                    onInquiryClick = {},
                    onBackClick = {
                        viewModel.isCamera.value = false
                        navController.navigate(MainPage.Search.name){
                            popUpTo(MainPage.Search.name){
                                inclusive = true
                            }
                        }
                    }
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
                        getBitmap(viewModel)
                    } else {
                        null
                    }
                )
                InquiryTextTextField(
                    content = content,
                    onValueChange = {
                        title = it
                    }
                )
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

