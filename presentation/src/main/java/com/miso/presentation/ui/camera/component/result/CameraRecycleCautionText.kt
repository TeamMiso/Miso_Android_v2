package com.miso.presentation.ui.camera.component.result

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraRecycleCautionText() {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "❗️유의할 점",
                style = typography.textMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.BLACK
            )
        }
    }
}