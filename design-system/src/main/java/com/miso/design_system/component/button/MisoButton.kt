package com.miso.design_system.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.textfield.MisoTextField
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        Row(modifier = modifier) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colors.PRIMARY),
                onClick = { onClick() }
            ) {
                Text(
                    text = text,
                    color = colors.WHITE,
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
    MisoButton(
        text = "버튼",
        onClick = {}
    )
}