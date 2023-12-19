package com.miso.presentation.ui.inquiry.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryListDetailContentTitleText(text: String) {
    MisoTheme { colors, typography ->
        Text(
            text = text,
            style = typography.textMedium,
            fontWeight = FontWeight.SemiBold,
            color = colors.BLACK
        )
    }
}