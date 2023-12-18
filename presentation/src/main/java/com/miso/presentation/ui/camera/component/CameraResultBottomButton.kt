package com.miso.presentation.ui.camera.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultBottomButton(
    onRecaptureClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = colors.GREYSCALE2)
        ) {
            CameraRecaptureButton { onRecaptureClick() }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                CameraConfirmButton { onConfirmClick() }
            }
        }
    }
}

@Composable
fun CameraRecaptureButton(
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Button(
            modifier = Modifier.size(144.dp, 64.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { onClick() }
        ) {
            Text(
                text = "다시 찍기",
                style = typography.buttonLarge,
                fontWeight = FontWeight(500),
                color = colors.WHITE
            )
        }
    }
}

@Composable
fun CameraConfirmButton(
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Button(
            modifier = Modifier.size(144.dp,64.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { onClick() }
        ) {
            Box(
                modifier = Modifier.size(64.dp, 48.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "확인",
                    style = typography.buttonLarge,
                    fontWeight = FontWeight(500),
                    color = colors.WHITE
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraResultBottomButtonPreview() {
    CameraResultBottomButton( onRecaptureClick = {}, onConfirmClick = {} )
}