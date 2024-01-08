package com.miso.presentation.ui.result.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.result.component.RecycleCautionText
import com.miso.presentation.ui.result.component.RecycleContentText
import com.miso.presentation.ui.result.component.RecycleImage
import com.miso.presentation.ui.result.component.RecycleMethodText
import com.miso.presentation.ui.result.component.RecycleTipText
import com.miso.presentation.ui.result.component.ResultRecyclablesTypeText
import com.miso.presentation.ui.result.component.ResultSubTitleText
import com.miso.presentation.ui.result.component.ResultTitleText
import com.miso.presentation.viewmodel.RecyclablesViewModel

@Composable
fun ResultScreen(
    viewModel: RecyclablesViewModel,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
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
                MisoBlackTitleText(text = viewModel.result.value.title)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        RecycleImage(imageUrl = viewModel.result.value.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            ResultTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            ResultSubTitleText(text = viewModel.result.value.subTitle)
            Spacer(modifier = Modifier.height(8.dp))
            ResultRecyclablesTypeText(text = viewModel.result.value.recyclablesType, imageUrl = viewModel.result.value.recycleMark)
            Spacer(modifier = Modifier.height(16.dp))
            RecycleMethodText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(viewModel.result.value.recycleMethod)
            Spacer(modifier = Modifier.height(16.dp))
            RecycleTipText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(viewModel.result.value.recycleTip)
            Spacer(modifier = Modifier.height(16.dp))
            RecycleCautionText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText(viewModel.result.value.recycleCaution)
            Spacer(modifier = Modifier.height(56.dp))
            MisoButton(
                text = "홈으로"
            ) {
                onSearchClick()
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}