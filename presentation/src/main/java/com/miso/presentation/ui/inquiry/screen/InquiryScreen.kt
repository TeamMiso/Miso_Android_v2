package com.miso.presentation.ui.inquiry.screen

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryImageButton
import com.miso.presentation.ui.inquiry.component.InquiryTextTextField
import com.miso.presentation.ui.inquiry.component.InquiryTitleTextField
import com.miso.presentation.ui.inquiry.component.InquiryTopBar
import com.miso.presentation.ui.inquiry.component.bottomsheet.SelectPhotoPathBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InquiryScreen() {
    var bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val bottomSheetScope = rememberCoroutineScope()

    var imageUri by remember { mutableStateOf(Uri.EMPTY) }

    ModalBottomSheetLayout(
        sheetContent = {
            SelectPhotoPathBottomSheet(
                bottomSheetState = bottomSheetState,
                selectedImageUri = { uri ->
                    imageUri = uri
                },
                onCameraClick = {}
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
                InquiryTopBar(onInquiryClick = {}, onBackClick = {})
                InquiryTitleTextField()
                InquiryImageButton(
                    onClick = {
                        bottomSheetScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    selectedImageUri = imageUri,
                    capturedImage = null
                )
                InquiryTextTextField()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryScreenPreview() {
    InquiryScreen()
}