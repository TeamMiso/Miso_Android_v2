package com.miso.design_system.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miso.design_system.component.modifier.misoClickable
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        Row(
            modifier = modifier.misoClickable { onClick() },
        ) {
            Text(
                text = "< 돌아가기",
                style = typography.textMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.BULE1
            )
        }
    }
}