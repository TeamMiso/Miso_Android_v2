package com.miso.presentation.ui.camera.screen

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.camera.component.CameraResultBottomButton
import com.miso.presentation.ui.camera.component.CameraResultPreview
import com.miso.presentation.viewmodel.CameraViewModel

@Composable
fun CameraResultScreen(
    context: Context,
    viewModel: CameraViewModel,
    navController: NavController
) {
    val imageBitmap = getBitmap(viewModel = viewModel)

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
                }
            )
        }
    }
}

@Composable
private fun getBitmap(viewModel: CameraViewModel): ImageBitmap? {
    val captureImgBitmapState by viewModel.captureImgBitmapState.collectAsState()
    LaunchedEffect(captureImgBitmapState) { Log.d("testt", captureImgBitmapState.toString()) }
    (captureImgBitmapState.capturedImage?.asImageBitmap() ?: null)?.let {
        return it
    }
    return null
}