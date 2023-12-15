package com.miso.design_system.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.miso.design_system.icon.MisoColorIcon
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoLogoTitleText() {
    MisoTheme { colors, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            MisoColorIcon()
            Text(
                text = "MISO",
                style = typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.PRIMARY
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MisoLogoTitleTextPreview() {
    MisoLogoTitleText()
}