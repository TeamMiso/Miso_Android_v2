package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryTopBar(
    onInquiryClick: () -> Unit,
    onBackClick: () -> Unit,
    isUser: Boolean
) {
    MisoTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(colors.WHITE)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.size(120.dp,48.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = {}
            ) {
                MisoBackButton { onBackClick() }
            }
            InquiryText()
            InquiryButton(onClick = { onInquiryClick() }, isUser = isUser)
        }
    }
}

@Composable
fun InquiryText() {
    MisoTheme { colors, typography ->
        Text(
            text = "문의하기",
            style = typography.buttonSmall,
            fontWeight = FontWeight.Medium,
            color = colors.BLACK
        )
    }
}

@Composable
fun InquiryButton(
    onClick: () -> Unit,
    isUser: Boolean
) {
    MisoTheme { colors, typography ->
        Button(
            modifier = Modifier.size(104.dp,48.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { onClick() }
        ) {
            Box(
                modifier = Modifier.size(87.dp, 32.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if(isUser){
                    Text(
                        text = "문의하기",
                        style = typography.textMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.PRIMARY
                    )
                } else {
                    Text(
                        text = "답변하기",
                        style = typography.textMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.PRIMARY
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryTopBarPreview() {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        InquiryTopBar( onInquiryClick = {}, onBackClick = {}, false)
    }
}
