package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun UserPointText(text: String) {
    MisoTheme { colors, typography ->
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = com.miso.design_system.R.drawable.ic_shop),
                contentDescription = "Chip Component Icon",
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = text,
                style = typography.captionLarge,
                fontWeight = FontWeight.SemiBold,
                color = colors.GREYSCALE3
            )
        }
    }
}