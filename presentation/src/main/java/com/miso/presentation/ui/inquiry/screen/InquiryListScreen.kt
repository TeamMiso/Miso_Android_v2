package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.R
import com.miso.design_system.component.chip.MisoChip
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.presentation.ui.inquiry.component.InquiryList
import com.miso.presentation.ui.inquiry.component.InquiryListTitleText

@Composable
fun InquiryListScreen(
    onInquiryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MisoLogoTitleText()
            MisoChip(
                text = "문의하기",
                icon = R.drawable.ic_plus
            ) {
                onInquiryClick()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        InquiryListTitleText()
        Spacer(modifier = Modifier.height(16.dp))
        InquiryList(
            list = listOf("첫번째", "두번째", "세번째"),
            onItemClick = {}
        )
    }
}