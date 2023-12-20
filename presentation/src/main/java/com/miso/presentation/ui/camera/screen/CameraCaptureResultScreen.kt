package com.miso.presentation.ui.camera.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.design_system.component.lottie.MisoLoadingLottie
import com.miso.design_system.theme.MisoTheme
import com.miso.domain.model.recyclables.response.AiListResponseModel
import com.miso.presentation.ui.camera.CameraPage
import com.miso.presentation.ui.camera.component.CameraResultBottomButton
import com.miso.presentation.ui.camera.component.CameraResultPreview
import com.miso.presentation.ui.util.formatNumber
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun CameraCaptureResultScreen(
    viewModel: CameraViewModel,
    navController: NavController,
    onSearch: (aiAnswer: AiListResponseModel) -> Unit,
    onDismissClick: () -> Unit,
    onGoInquiry: (byteArray: ByteArray) -> Unit
) {
    val imageBitmap = viewModel.getBitmap()

    val launchAi = remember { mutableStateOf(false) }

    var progressState = remember { mutableStateOf(false) }

    var openDialog = remember { mutableStateOf(false) }

    LaunchedEffect(launchAi.value){
        if(launchAi.value){
            getAiResponse(
                viewModel = viewModel,
                progressState = { progressState.value = it },
                onSuccess = {response ->
                    onSearch(response)
                },
                onFailure = {
                    openDialog.value = true
                    launchAi.value = false
                },
                onError = {
                    launchAi.value = false
                }
            )
        }
    }

    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.BLACK),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(136.dp))
            CameraResultPreview(
                if (imageBitmap != null) {
                    imageBitmap!!
                } else {
                    null
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            contentAlignment = Alignment.BottomCenter
        ) {
            CameraResultBottomButton(
                onRecaptureClick = { navController.popBackStack() },
                onConfirmClick = {
                    val sendMultipartFile = viewModel.getMultipartFile()
                    viewModel.getAiList(sendMultipartFile)
                    launchAi.value = true
                }
            )
        }
        if (progressState.value) {
            Dialog(onDismissRequest = {}) {
                MisoLoadingLottie()
            }
        }
        if (openDialog.value) {
            MisoDialog(
                openDialog = openDialog.value,
                onStateChange = {
                    openDialog.value = it
                },
                title = "검색 결과 없음",
                content = "미소가 아직 본 적 없는 쓰레기에요.\n" +
                        "문의하기로 미소에게 가르쳐주실래요?",
                dismissText = "홈으로",
                checkText = "문의하러 가기",
                onDismissClick = { onDismissClick() },
                onCheckClick = { onGoInquiry(viewModel.swapBitmapToJpeg()) }
            )
        }
    }
}

suspend fun getAiResponse(
    viewModel: CameraViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (aiAnswer: AiListResponseModel) -> Unit,
    onFailure: () -> Unit,
    onError: () -> Unit
) {
    viewModel.aiListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                progressState(false)
                onSuccess(response.data!!)
            }

            is Event.NotFound -> {
                progressState(false)
                onFailure()
            }

            is Event.Loading -> {
                progressState(true)
            }

            else -> {
                progressState(false)
                onError()
            }
        }
    }
}