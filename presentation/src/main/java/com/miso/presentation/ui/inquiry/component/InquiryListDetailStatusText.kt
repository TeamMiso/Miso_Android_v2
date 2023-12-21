package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryListDetailStatusText(
    date: String,
    status: String
) {
    MisoTheme { colors, typography ->
        Row {
            Text(
                text = date,
                style = typography.textSmall,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE2
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (status == "WAIT") {
                Text(
                    text = "검토 중",
                    style = typography.textSmall,
                    fontWeight = FontWeight.Normal,
                    color = colors.GREYSCALE3
                )
            } else {
                Text(
                    text = "답변 완료",
                    style = typography.captionLarge,
                    fontWeight = FontWeight.Normal,
                    color = colors.PRIMARY
                )
            }
        }
    }
}