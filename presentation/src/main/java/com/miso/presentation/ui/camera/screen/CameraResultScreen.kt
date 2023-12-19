package com.miso.presentation.ui.camera.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.camera.component.result.CameraWrongAnswerButton
import com.miso.presentation.ui.result.component.RecycleCautionText
import com.miso.presentation.ui.result.component.RecycleContentText
import com.miso.presentation.ui.result.component.RecycleImage
import com.miso.presentation.ui.result.component.RecycleMethodText
import com.miso.presentation.ui.result.component.RecycleTipText
import com.miso.presentation.ui.result.component.ResultRecyclablesTypeText
import com.miso.presentation.ui.result.component.ResultSubTitleText
import com.miso.presentation.ui.result.component.ResultTitleText
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.RecyclablesViewModel

@Composable
fun CameraResultScreen(
    viewModel: CameraViewModel,
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

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
                MisoBlackTitleText(text = "")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        RecycleImage(imageUrl = "")
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            ResultTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            ResultSubTitleText(text = "")
            Spacer(modifier = Modifier.height(8.dp))
            ResultRecyclablesTypeText(text = "", imageUrl = "")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleMethodText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(text = "")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleTipText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(text = "")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleCautionText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(text = "")
            Spacer(modifier = Modifier.height(56.dp))
            MisoButton(
                modifier = Modifier,
                text = "10 포인트 받기"
            ) {}
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CameraWrongAnswerButton( onClick = {})
            }
        }
    }
}