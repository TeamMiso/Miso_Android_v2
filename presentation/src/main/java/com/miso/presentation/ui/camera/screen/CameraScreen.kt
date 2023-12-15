package com.miso.presentation.ui.camera.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miso.presentation.ui.camera.component.CameraBackButton
import com.miso.presentation.ui.camera.component.CameraCaptureButton
import com.miso.presentation.ui.camera.component.CameraFlashButton
import com.miso.presentation.ui.camera.component.CameraPreview

@Composable
fun CameraScreen(
    context: Context,
) {
    CameraPreview( context = context )
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 56.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer( modifier = Modifier.width(16.dp) )
            CameraBackButton {}
            Spacer( modifier = Modifier.width(96.dp) )
            CameraCaptureButton {}
            Spacer( modifier = Modifier.width(96.dp) )
            CameraFlashButton {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraScreenPreview() {
    CameraScreen(context = LocalContext.current)
}