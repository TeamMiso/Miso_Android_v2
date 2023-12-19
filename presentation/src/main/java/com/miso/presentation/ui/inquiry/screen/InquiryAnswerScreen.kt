package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryContentTextField
import com.miso.presentation.ui.inquiry.component.InquiryDateText
import com.miso.presentation.ui.inquiry.component.InquiryTitleText
import com.miso.presentation.ui.inquiry.component.InquiryTopBar

@Composable
fun InquiryAnswerScreen() {
    var content by remember { mutableStateOf("") }
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .statusBarsPadding()
        ) {
            InquiryTopBar(onInquiryClick = {}, onBackClick = {},isUser = false)
            Spacer(modifier = Modifier.height(8.dp))
            InquiryTitleText(title = "실패하면 반역, 성공하면 혁명 아입니까?!")
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                InquiryDateText(date = "1979.12.12")
            }
            InquiryContentTextField(
                content = content,
                onValueChange = {
                    content = it
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryAnswerScreenPreview() {
    InquiryAnswerScreen()
}