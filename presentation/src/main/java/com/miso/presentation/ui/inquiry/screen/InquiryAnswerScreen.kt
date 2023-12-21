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
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryContentTextField
import com.miso.presentation.ui.inquiry.component.InquiryDateText
import com.miso.presentation.ui.inquiry.component.InquiryTitleText
import com.miso.presentation.ui.inquiry.component.InquiryTopBar
import com.miso.presentation.ui.util.toDateString
import com.miso.presentation.viewmodel.InquiryViewModel

@Composable
fun InquiryAnswerScreen(
    viewModel: InquiryViewModel,
    onBackClick: () -> Unit
) {
    var content by remember { mutableStateOf("") }

    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .statusBarsPadding()
        ) {
            InquiryTopBar(
                onInquiryClick = {

                },
                onBackClick = {
                    onBackClick()
                },
                isUser = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InquiryTitleText(title = viewModel.inquiryListDetail.value.title)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                InquiryDateText(date = viewModel.inquiryListDetail.value.inquiryDate.toDateString())
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