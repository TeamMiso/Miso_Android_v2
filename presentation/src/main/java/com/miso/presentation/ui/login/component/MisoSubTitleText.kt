package com.miso.presentation.ui.login.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoSubTitleText() {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "환경을 웃음으로 바꾸다 :)",
                style = typography.textMedium,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE3
            )
        }
    }
}