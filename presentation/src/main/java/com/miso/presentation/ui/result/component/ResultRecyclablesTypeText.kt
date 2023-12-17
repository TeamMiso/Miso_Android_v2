package com.miso.presentation.ui.result.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miso.design_system.theme.MisoTheme

@Composable
fun ResultRecyclablesTypeText(
    text: String,
    imageUrl: String
) {
    MisoTheme { colors, typography ->
        Row {
            Text(
                text = "분류: $text",
                color = colors.BLACK,
                style = typography.textSmall,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(4.dp))
            AsyncImage(
                model = imageUrl,
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    }
}