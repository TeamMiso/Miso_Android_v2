package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryListTitleText() {
    MisoTheme { colors, typography ->
        Text(
            text = "문의 내역",
            style = typography.textMedium,
            fontWeight = FontWeight.SemiBold,
            color = colors.BLACK
        )
    }
}