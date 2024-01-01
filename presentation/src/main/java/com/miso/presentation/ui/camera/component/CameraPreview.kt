package com.miso.presentation.ui.camera.component

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.camera.util.capturePhoto

@Composable
fun CameraPreview(
    context: Context,
    onPhotoCaptured: (Boolean) -> Unit,
    onPhotoCapturedData: (Bitmap) -> Unit,
    isFlashOn: Boolean
) {

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }

    MisoTheme { colors, typography ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 32.dp, bottom = 32.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CameraCaptureButton {
                        capturePhoto(
                            context = context,
                            cameraController = cameraController,
                            onPhotoCaptured = { captured -> onPhotoCaptured(captured) },
                            onPhotoCapturedData = { bitmap -> onPhotoCapturedData(bitmap) },
                        )
                    }
                }
            }
        ) { innerPadding: PaddingValues ->

            cameraController.enableTorch(isFlashOn)

            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                factory = { context ->
                    PreviewView(context).apply {
                        setBackgroundColor(Color.LightGray.toArgb())
                        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }.also { previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                },
                onRelease = {
                    cameraController.unbind()
                }
            )
        }
    }
}