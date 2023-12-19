package com.miso.presentation.ui.setting.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun EmailText(text: String) {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text,
                style = typography.textMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.GREYSCALE2
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "에 로그인됨",
                style = typography.textMedium,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE3
            )
        }
    }
}