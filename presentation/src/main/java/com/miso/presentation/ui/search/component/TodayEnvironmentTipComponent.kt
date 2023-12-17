package com.miso.presentation.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.icon.HideButtonIcon
import com.miso.design_system.theme.MisoTheme

@Composable
fun TodayEnvironmentTipComponent(
    modifier: Modifier,
    onClick: () -> Unit
) {
    var isHidden by remember { mutableStateOf(false) }

    if (!isHidden) {
        MisoTheme { colors, typography ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = colors.PRIMARY,
                        shape = RoundedCornerShape(999.dp)
                    )
                    .background(
                        color = colors.WHITE,
                        shape = RoundedCornerShape(999.dp)
                    )
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onClick() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "오늘의 재활용 팁 보러가기 >>",
                    style = typography.textSmall,
                    fontWeight = FontWeight.Medium,
                    color = colors.PRIMARY
                )
                Box(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { isHidden = true }
                ) {
                    HideButtonIcon()
                }
            }
        }
    }
}