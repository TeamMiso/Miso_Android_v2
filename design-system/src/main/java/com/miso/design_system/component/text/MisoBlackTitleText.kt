package com.miso.design_system.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoBlackTitleText(
    modifier: Modifier = Modifier,
    text: String,
) {
    MisoTheme { colors, typography ->
        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                text = text,
                style = typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                color = colors.BLACK
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MisoBlackTitleTextPreview() {
    MisoBlackTitleText(text = "타이틀")
}