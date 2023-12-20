package com.miso.presentation.ui.camera.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraResultInconsistencyText() {
    MisoTheme { colors, typography ->
        Text(
            text = "인식된 모든 품목",
            modifier = Modifier.size(211.dp, 48.dp),
            style = typography.titleSmall,
            fontWeight = FontWeight.ExtraBold,
            color = colors.BLACK
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CameraResultInconsistencyTextPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CameraResultInconsistencyText()
    }
}