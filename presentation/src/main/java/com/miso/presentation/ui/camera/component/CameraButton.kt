package com.miso.presentation.ui.camera.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun CameraCaptureButton(onClick: () -> Unit){
    MisoTheme { colors, typography ->
        IconButton(
            modifier = Modifier
                .size(64.dp, 64.dp),
            onClick = { onClick() }
        ) {
            Image(
                painter = painterResource(id = com.miso.design_system.R.drawable.ic_camera_capure_button),
                contentDescription = "Camera Btn",
            )
        }
    }
}
@Composable
fun CameraBackButton(onClick: () -> Unit){
    MisoTheme { colors, typography ->
        Button(
            modifier = Modifier
                .size(48.dp,48.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { onClick() }
        ) {
            Text(
                text = "<",
                style = typography.buttonSmall,
                color = colors.WHITE,
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Right
            )
        }
    }
}

@Composable
fun CameraFlashButton(onClick: () -> Unit){

    var isFlashOn by remember { mutableStateOf(false) }

    MisoTheme { colors, typography ->
        IconButton(
            onClick = {
                onClick()
                isFlashOn = !isFlashOn
            },
        ) {
            if(!isFlashOn) {
                Image(
                    painter = painterResource(id = com.miso.design_system.R.drawable.ic_camera_flash_off_button),
                    contentDescription = "Flash Off Button",
                )

            }else {
                Image(
                    painter = painterResource(id = com.miso.design_system.R.drawable.ic_camera_flash_on_button),
                    contentDescription = "Flash On Button",
                )

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraButtonPreView() {
    Column(modifier = Modifier.fillMaxSize()){
        CameraCaptureButton( onClick = {} )
        CameraBackButton( onClick = {} )
        CameraFlashButton( onClick = {} )
    }
}