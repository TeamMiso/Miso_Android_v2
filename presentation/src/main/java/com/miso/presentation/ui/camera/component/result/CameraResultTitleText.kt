package com.miso.presentation.ui.camera.component.result

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultTitleText() {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "이 쓰레기는 어떤 쓰레기인가요?",
                style = typography.textMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.BLACK
            )
        }
    }
}