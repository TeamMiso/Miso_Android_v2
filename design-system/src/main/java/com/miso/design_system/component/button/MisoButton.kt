package com.miso.design_system.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoButton(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
    text: String,
    enabled: Boolean = true,
    state: ButtonState = ButtonState.Normal,
    onClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        val backgroundColor: (buttonState: ButtonState) -> Color = {
            when (it) {
                ButtonState.OutLine -> colors.WHITE
                ButtonState.Normal -> if (enabled) colors.PRIMARY else colors.PRIMARY.copy(0.38f)
            }
        }

        val contentColor: (buttonState: ButtonState) -> Color = {
            when (it) {
                ButtonState.OutLine -> colors.GREYSCALE3
                ButtonState.Normal -> colors.WHITE
            }
        }

        Row(modifier = modifier) {
            Button(
                modifier = modifier
                    .border(
                        width = 2.dp,
                        color =
                        if (state == ButtonState.OutLine)
                            colors.GREYSCALE3
                        else colors.WHITE.copy(
                            alpha = 0f
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor(state),
                    contentColor = contentColor(state)
                ),
                onClick = { if (enabled) onClick() }
            ) {
                Text(
                    text = text,
                    style = typography.buttonLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MisoButtonPreview() {
    Column {
        MisoButton(
            text = "버튼",
            onClick = {}
        )
        MisoButton(
            text = "버튼",
            state = ButtonState.OutLine,
            onClick = {}
        )
        MisoButton(
            text = "버튼",
            enabled = false,
            onClick = {}
        )
    }
}