package com.miso.presentation.ui.camera.component.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultSubTitleText(text: String) {
    MisoTheme { colors, typography ->
        Text(
            text = text,
            color = colors.BULE1,
            style = typography.textSmall,
            fontWeight = FontWeight.Normal
        )
    }
}