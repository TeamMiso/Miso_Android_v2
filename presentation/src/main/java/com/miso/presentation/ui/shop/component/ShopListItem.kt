package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.util.formatNumber

@Composable
fun ShopListItem(
    name: String,
    price: Int,
    image: String,
    onItemClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(0.5f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onItemClick() }
                )
        ) {
            AsyncImage(
                model = image,
                modifier = Modifier
                    .fillMaxSize(0.95f)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = typography.textMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${formatNumber(price)} Point",
                style = typography.textSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.GREYSCALE2
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
