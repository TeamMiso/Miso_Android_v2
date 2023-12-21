package com.miso.presentation.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipContentText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipImage
import com.miso.presentation.ui.search.component.TodayEnvironmentTipTitleText
import com.miso.presentation.viewmodel.EnvironmentViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun TodayEnvironmentTipScreen(
    viewModel: EnvironmentViewModel,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect("Environment") {
        viewModel.getEnvironment()
    }

    LaunchedEffect("GetEnvironment") {
        getEnvironment(viewModel = viewModel)
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
                MisoBlackTitleText(text = "오늘의 환경 지식")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        TodayEnvironmentTipImage(imageUrl = viewModel.environment.value.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TodayEnvironmentTipTitleText(text = viewModel.environment.value.title)
            Spacer(modifier = Modifier.height(8.dp))
            TodayEnvironmentTipContentText(text = viewModel.environment.value.content)
        }
    }
}

suspend fun getEnvironment(
    viewModel: EnvironmentViewModel
) {
    viewModel.getEnvironmentResponse.collect {
        if (it is Event.Success) {
            viewModel.saveEnvironment(it.data!!)
        }
    }
}