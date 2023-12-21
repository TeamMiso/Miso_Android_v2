package com.miso.presentation.ui.search.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun TodayEnvironmentTipImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.34f),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}