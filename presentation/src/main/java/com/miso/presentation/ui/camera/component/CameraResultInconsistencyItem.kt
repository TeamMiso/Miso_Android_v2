package com.miso.presentation.ui.camera.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultInconsistencyItem(
    title: String,
    content: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CameraResultInconsistencyItemTitle(title)
            CameraResultInconsistencyItemContent(content)
        }
        CameraResultInconsistencyItemImage(imageUrl)
    }
}

@Composable
fun CameraResultInconsistencyItemTitle(
    text: String
) {
    MisoTheme { colors, typography ->
        Text(
            text = text,
            modifier = Modifier
                .wrapContentSize(),
            style = typography.textSmall,
            fontWeight = FontWeight.SemiBold,
            color = colors.GREYSCALE2
        )
    }
}

@Composable
fun CameraResultInconsistencyItemContent(
    text: String
) {
    MisoTheme { colors, typography ->
        Text(
            text = text,
            modifier = Modifier.size(264.dp, 16.dp),
            style = typography.captionLarge,
            fontWeight = FontWeight.Normal,
            color = colors.GREYSCALE2
        )
    }
}

@Composable
fun CameraResultInconsistencyItemImage(
    imageUrl: String
) {
    MisoTheme { colors, _ ->
        AsyncImage(
            model = imageUrl,
            modifier = Modifier
                .size(48.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(colors.GREYSCALE4),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CameraResultInconsistencyItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CameraResultInconsistencyItem(title = "폴리에틸렌(PE)", content = "붙어 있는 모든 라벨을 제거하고 재활용할 수 있도록 깨끗하게 씻어서 배출", imageUrl = "")
    }
}