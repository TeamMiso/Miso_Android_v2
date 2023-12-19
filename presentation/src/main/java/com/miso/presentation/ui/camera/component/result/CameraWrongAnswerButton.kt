package com.miso.presentation.ui.camera.component.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraWrongAnswerButton(
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Button(
            modifier = Modifier.size(140.dp,48.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { onClick() }
        ) {
            Column(
                modifier = Modifier.size(112.dp, 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "이 결과가 아닌가요?",
                    style = typography.captionLarge,
                    fontWeight = FontWeight.Normal,
                    color = colors.BULE1
                )
                Divider(
                    color = colors.BULE1,
                    modifier = Modifier
                        .width(96.dp)
                        .height(1.dp)
                )
            }
        }
    }
}