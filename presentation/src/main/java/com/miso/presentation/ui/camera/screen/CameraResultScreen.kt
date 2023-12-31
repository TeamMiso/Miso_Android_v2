package com.miso.presentation.ui.camera.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.camera.component.result.CameraRecycleCautionText
import com.miso.presentation.ui.camera.component.result.CameraRecycleContentText
import com.miso.presentation.ui.camera.component.result.CameraRecycleImage
import com.miso.presentation.ui.camera.component.result.CameraRecycleMethodText
import com.miso.presentation.ui.camera.component.result.CameraRecycleTipText
import com.miso.presentation.ui.camera.component.result.CameraResultRecyclablesTypeText
import com.miso.presentation.ui.camera.component.result.CameraResultSubTitleText
import com.miso.presentation.ui.camera.component.result.CameraResultTitleText
import com.miso.presentation.ui.camera.component.result.CameraWrongAnswerButton
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.UserViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun CameraResultScreen(
    viewModel: CameraViewModel,
    userViewModel: UserViewModel,
    onBackClick: () -> Unit,
    onPointClick: () -> Unit,
    onInconsistencyClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    val givePointState = remember { mutableStateOf(false) }
    val progressState = remember { mutableStateOf(false) }

    var openDialog = remember { mutableStateOf(false) }

    LaunchedEffect(givePointState.value){
        if(givePointState.value){
            givePoint(
                viewModel = userViewModel,
                progressState = { progressState.value = it },
                onSuccess = { openDialog.value = true }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            MisoBackButton {
                onBackClick()
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                MisoBlackTitleText(text = viewModel.result.value.title)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        CameraRecycleImage(viewModel.getBitmap()!!)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            CameraResultTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            CameraResultSubTitleText(text = viewModel.result.value.subTitle)
            Spacer(modifier = Modifier.height(8.dp))
            CameraResultRecyclablesTypeText(text = viewModel.result.value.recyclablesType, imageUrl = viewModel.result.value.recycleMark)
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleMethodText()
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleContentText(text = viewModel.result.value.recycleMethod)
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleTipText()
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleContentText(text = viewModel.result.value.recycleTip)
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleCautionText()
            Spacer(modifier = Modifier.height(16.dp))
            CameraRecycleContentText(text = viewModel.result.value.recycleCaution)
            Spacer(modifier = Modifier.height(56.dp))
            MisoButton(
                text = "100 포인트 받기"
            ) {
                userViewModel.givePoint()
                givePointState.value = true
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CameraWrongAnswerButton(
                    onClick = {
                        onInconsistencyClick()
                    }
                )
            }
        }
    }
    if (openDialog.value) {
        MisoDialog(
            openDialog = openDialog.value,
            onStateChange = {
                openDialog.value = it
            },
            title = "100포인트 획득",
            content = "포인트가 지급되었어요!\n" +
                      "홈 화면으로 돌아갈게요 :)",
            dismissText = "",
            checkText = "홈으로",
            onDismissClick = {},
            onCheckClick = { onPointClick() }
        )
    }
}

suspend fun givePoint(
    viewModel: UserViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: () -> Unit,
) {
    viewModel.givePointResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                Log.d("givePoint", "이벤트 성공")
                onSuccess()
                progressState(false)
            }

            is Event.Loading -> {
                Log.d("givePoint", "이벤트 중")
                progressState(true)
            }

            else -> {
                Log.d("givePoint", "이벤트 실패")
                progressState(false)
            }
        }
    }
}