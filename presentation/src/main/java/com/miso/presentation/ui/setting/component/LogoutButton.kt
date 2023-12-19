package com.miso.presentation.ui.setting.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.icon.LogoutIcon
import com.miso.design_system.theme.MisoTheme

@Composable
fun LogoutButton(onClick: () -> Unit) {
    MisoTheme { colors, typography ->
        Row {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                border = BorderStroke(1.dp, colors.RED1),
                shape = RoundedCornerShape(8.dp),
                onClick = { onClick() }
            ) {
                LogoutIcon()
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "로그아웃",
                    style = typography.buttonLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.RED1
                )
            }
        }
    }
}