package com.miso.presentation.ui.inquiry.component

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
import com.miso.presentation.ui.search.component.SearchListItem

@Composable
fun InquiryListItem(
    title: String,
    date: String,
    status: String,
    imageUrl: String,
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
                Row {
                    Text(
                        text = date,
                        style = typography.captionLarge,
                        fontWeight = FontWeight.Normal,
                        color = colors.GREYSCALE2
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (status == "WAIT") {
                        Text(
                            text = "검토 중",
                            style = typography.captionLarge,
                            fontWeight = FontWeight.Normal,
                            color = colors.GREYSCALE3
                        )
                    } else {
                        Text(
                            text = "답변 완료",
                            style = typography.captionLarge,
                            fontWeight = FontWeight.Normal,
                            color = colors.PRIMARY
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = typography.textMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.BLACK,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
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
fun InquiryListItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        InquiryListItem(
            title = "안녕하세요",
            date = "24.01.01",
            status = "WAIT",
            imageUrl = "https://project-miso.s3.ap-northeast-2.amazonaws.com/file/Rectangle+2083.png",
            onItemClick = {}
        )
    }
}