package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun DetailContentText(text: String) {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text,
                style = typography.textSmall,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE2
            )
        }
    }
}