package com.miso.presentation.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun PushNotificationText() {
    MisoTheme { colors, typography ->
        Column {
            Text(
                text = "푸시 알림 켜기",
                style = typography.textSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.BLACK
            )
            Text(
                text = "문의 사항에 답변이 오면 알려드려요.",
                style = typography.captionLarge,
                fontWeight = FontWeight.Normal,
                color = colors.BLACK
            )
        }
    }
}