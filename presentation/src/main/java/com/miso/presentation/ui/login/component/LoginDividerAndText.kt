package com.miso.presentation.ui.login.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun LoginDividerAndText() {
    MisoTheme { colors, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp), color = colors.GREYSCALE3
            )
            Text(
                text = "회원이 아니신가요?",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = typography.captionLarge,
                fontWeight = FontWeight.Normal,
                color = colors.GREYSCALE3
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp), color = colors.GREYSCALE3
            )
        }
    }
}