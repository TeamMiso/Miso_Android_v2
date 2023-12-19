package com.miso.design_system.component.toggle

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoToggle(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onUpdate: (Boolean) -> Unit
) {
    MisoTheme { colors, typography ->
        val color by animateColorAsState(if (selected) colors.PRIMARY else colors.GREYSCALE3)

        Card(
            modifier = modifier
                .width(56.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onUpdate(!selected)
                },
            shape = RoundedCornerShape(16.dp),
            elevation = 0.dp
        ) {
            Box(
                modifier = Modifier.background(color),
                contentAlignment = if (selected) Alignment.TopEnd else Alignment.TopStart
            ) {
                CheckCircle(modifier = Modifier.padding(2.dp))
            }
        }
    }
}

@Composable
fun CheckCircle(
    modifier: Modifier = Modifier
) {
    MisoTheme { colors, typography ->
        Card(
            shape = CircleShape,
            modifier = modifier.size(28.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.background(colors.WHITE))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MisoTogglePreview() {
    Column {
        MisoToggle(
            selected = true,
            onUpdate = {}
        )
        MisoToggle(
            selected = false,
            onUpdate = {}
        )
    }
}