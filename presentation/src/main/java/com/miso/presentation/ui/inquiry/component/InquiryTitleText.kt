package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryTitleText(
    title: String
) {
    MisoTheme { colors, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE),
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            style = typography.titleSmall,
            fontWeight = FontWeight.ExtraBold,
            color = colors.GREYSCALE2
        )
    }
}