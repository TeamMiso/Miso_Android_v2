package com.miso.presentation.ui.inquiry.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.R

@Composable
fun InquiryImageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    selectedImageUri: Uri,
    capturedImage: ImageBitmap?
) {
    MisoTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colors.GREYSCALE3)
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (capturedImage != null) {
                    Image(
                        bitmap = capturedImage,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
                else if(selectedImageUri == Uri.EMPTY) {
                    Image(
                        painter = painterResource(id = com.miso.design_system.R.drawable.ic_inquiry_add_image),
                        contentDescription = "Gallery Logo Icon",
                        modifier = Modifier.size(96.dp, 96.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "이미지 추가하기",
                        color = colors.WHITE,
                        style = typography.textMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = selectedImageUri),
                        contentDescription = "Gallery Logo Icon",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    InquiryDeleteImageButton {}
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End,
            ) {
                if(selectedImageUri != Uri.EMPTY) InquiryDeleteImageButton {}
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryImageButtonPreview() {
    InquiryImageButton(selectedImageUri = Uri.EMPTY, onClick = {}, capturedImage = null)
}