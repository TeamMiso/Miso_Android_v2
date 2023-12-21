package com.miso.presentation.ui.inquiry.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.miso.design_system.R
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryDeleteImageButton(
    onClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        IconButton(
            modifier = Modifier
                .size(56.dp, 56.dp),
            onClick = { onClick() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete_image_button),
                contentDescription = "Camera Btn",
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryDeleteImageButtonPreview() {
    InquiryDeleteImageButton(onClick = {})
}