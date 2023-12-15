package com.miso.design_system.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.R
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoChip(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_delete_btn,
    text: String,
    onClick: () -> Unit
) {
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }

    MisoTheme { colors, typography ->
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 5.5.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                backgroundColor = colors.GREYSCALE3
                                onClick()
                                this.awaitRelease()
                                backgroundColor = Color.Transparent
                            },
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Chip Component Icon",
                    tint = colors.GREYSCALE3
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = text,
                    style = typography.textSmall,
                    fontWeight = FontWeight.Medium,
                    color = colors.GREYSCALE3
                )
            }
        }
    }
}

@Preview
@Composable
fun MisoChipPreview() {
    MisoChip(text = "text") {

    }
}