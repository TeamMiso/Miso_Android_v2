package com.miso.presentation.ui.camera.component.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CameraRecycleImage(image: ImageBitmap) {
    Image(
        bitmap = image,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}