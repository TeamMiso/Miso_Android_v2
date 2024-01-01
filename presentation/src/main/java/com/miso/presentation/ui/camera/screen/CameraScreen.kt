package com.miso.presentation.ui.camera.screen

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.camera.CameraPage
import com.miso.presentation.ui.camera.component.CameraBackButton
import com.miso.presentation.ui.camera.component.CameraFlashButton
import com.miso.presentation.ui.camera.component.CameraPreview
import com.miso.presentation.viewmodel.CameraViewModel

@Composable
fun CameraScreen(
    context: Context,
    viewModel: CameraViewModel,
    navController: NavController,
    onBackClick: () -> Unit,
    onInquiryCapture: (ByteArray) -> Unit
) {
    var isFlashOn = remember { mutableStateOf(false) }
    MisoTheme { colors, typography ->
        CameraPreview(
            context = context,
            onPhotoCaptured = { captured ->
                if (captured && !viewModel.isInquiry.value) {
                    navController.navigate(CameraPage.CameraCaptureResult.value)
                } else if(captured && viewModel.isInquiry.value){
                    onInquiryCapture(viewModel.swapBitmapToJpeg())
                }
            },
            onPhotoCapturedData = viewModel::loadImgBitmap,
            isFlashOn = isFlashOn.value
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 56.dp)
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CameraBackButton { onBackClick() }
                CameraFlashButton { flashState -> isFlashOn.value = flashState }
            }
        }
    }
}

