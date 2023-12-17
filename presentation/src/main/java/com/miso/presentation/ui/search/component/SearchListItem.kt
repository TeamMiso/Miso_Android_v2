package com.miso.presentation.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miso.design_system.theme.MisoTheme

@Composable
fun SearchListItem(
    title: String,
    content: String,
    image: String,
    type: String,
    onItemClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onItemClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$title ($type)",
                    style = typography.textSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.GREYSCALE2
                )
                Text(
                    text = content,
                    style = typography.captionLarge,
                    fontWeight = FontWeight.Normal,
                    color = colors.GREYSCALE2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (image.isNotBlank()) {
                AsyncImage(
                    model = image,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(colors.GREYSCALE4),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShopProductListItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        SearchListItem(
            title = "안녕하세요",
            content = "그아아ㅏ아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ아ㅏㅏㅏ아ㅏㅏ아ㅏ아ㅏㅏ아ㅏㅏ아ㅏㅏㅏㅏㅏㅏㅏ아ㅏㅏ아ㅏㅏㅏㅏㅏ아ㅏㅏㅏㅏ아ㅏㅏ아ㅏㅏㅏ아ㅏㅏㅏ아ㅏㅏㅏㅏㅏㅏ아ㅏㅏㅏㄱ",
            image = "https://project-miso.s3.ap-northeast-2.amazonaws.com/file/Rectangle+2083.png",
            type = "PATE",
            onItemClick = {}
        )
    }
}