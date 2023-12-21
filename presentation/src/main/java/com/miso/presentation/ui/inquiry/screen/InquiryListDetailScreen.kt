package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryButton
import com.miso.presentation.ui.inquiry.component.InquiryListDetailContentText
import com.miso.presentation.ui.inquiry.component.InquiryListDetailContentTitleText
import com.miso.presentation.ui.inquiry.component.InquiryListDetailImage
import com.miso.presentation.ui.inquiry.component.InquiryListDetailStatusText
import com.miso.presentation.ui.util.toDateString
import com.miso.presentation.viewmodel.InquiryViewModel
import com.miso.presentation.viewmodel.NotificationViewModel
import com.miso.presentation.viewmodel.UserViewModel

@Composable
fun InquiryListDetailScreen(
    inquiryViewModel: InquiryViewModel,
    notificationViewModel: NotificationViewModel,
    userViewModel: UserViewModel,
    onBackClick: () -> Unit,
    onInquiryAnswerClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(scrollState)
        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    MisoBackButton {
                        onBackClick()
                    }
                    if (inquiryViewModel.inquiryListDetail.value.inquiryStatus == "WAIT" &&
                        userViewModel.userInfo.value.role == "ROLE_ADMIN") {
                        InquiryButton(isUser = false) {
                            onInquiryAnswerClick()
                        }
                    }
                }
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    MisoBlackTitleText(text = inquiryViewModel.inquiryListDetail.value.title)
                    Spacer(modifier = Modifier.height(8.dp))
                    InquiryListDetailStatusText(
                        date = if (inquiryViewModel.inquiryListDetail.value.inquiryDate.isNotBlank()) {
                            inquiryViewModel.inquiryListDetail.value.inquiryDate.toDateString()
                        } else "",
                        status = inquiryViewModel.inquiryListDetail.value.inquiryStatus
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            InquiryListDetailImage(imageUrl = inquiryViewModel.inquiryListDetail.value.imageUrl ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                InquiryListDetailContentTitleText(text = "문의 내용")
                Spacer(modifier = Modifier.height(16.dp))
                InquiryListDetailContentText(
                    text = inquiryViewModel.inquiryListDetail.value.content
                )
                if (inquiryViewModel.inquiryListDetail.value.inquiryStatus == "COMPLETE") {
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier.height(1.dp),
                        color = colors.GREYSCALE3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    InquiryListDetailContentTitleText(text = "문의에 대한 답변")
                    Spacer(modifier = Modifier.height(16.dp))
                    InquiryListDetailContentText(
                        text = notificationViewModel.answer.value.answer
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}