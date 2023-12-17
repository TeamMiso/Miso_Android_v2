package com.miso.presentation.ui.camera.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.R
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultPreview(
    bitmap: ImageBitmap?
) {
    MisoTheme { colors, typography ->
        if (bitmap != null){
            Image(
                bitmap = bitmap,
                modifier = Modifier.size(328.dp, 400.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "camera result preview"
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_miso),
                modifier = Modifier.size(328.dp, 400.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "camera result preview"
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "사진을 잘라 더 정확히 검색해 보아요 :)",
            style = typography.textSmall,
            fontWeight = FontWeight(400),
            color = colors.GREYSCALE3
        )
    }
}