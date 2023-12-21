package com.miso.presentation.ui.search.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TodayEnvironmentTipImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}