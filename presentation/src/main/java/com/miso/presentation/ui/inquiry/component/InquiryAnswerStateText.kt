package com.miso.presentation.ui.inquiry.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryAnswerStateText(
    answer: Boolean
) {
    MisoTheme { colors, typography ->
        if(answer) {
            Text(
                text = "답변 완료",
                style = typography.titleSmall,
                fontWeight = FontWeight.Normal,
                color = colors.PRIMARY
            )
        } else {
            Text(
                text = "검토 중",
                style = typography.titleSmall,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE3
            )
        }
    }
}