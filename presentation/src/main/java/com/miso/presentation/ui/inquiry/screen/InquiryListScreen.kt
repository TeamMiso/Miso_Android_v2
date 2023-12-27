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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.R
import com.miso.design_system.component.chip.MisoChip
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.presentation.ui.inquiry.component.InquiryList
import com.miso.presentation.ui.inquiry.component.InquiryListTitleText
import com.miso.presentation.viewmodel.InquiryViewModel
import com.miso.presentation.viewmodel.NotificationViewModel
import com.miso.presentation.viewmodel.UserViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun InquiryListScreen(
    userViewModel: UserViewModel,
    inquiryViewModel: InquiryViewModel,
    notificationViewModel: NotificationViewModel,
    onInquiryClick: () -> Unit,
    onInquiryListDetailClick: () -> Unit
) {
    LaunchedEffect("InquiryList") {
        if (userViewModel.userInfo.value.role == "ROLE_USER") {
            inquiryViewModel.getInquiryList()
            getInquiryList(viewModel = inquiryViewModel)
        } else {
            inquiryViewModel.getInquiryListAll()
            getInquiryListAll(viewModel = inquiryViewModel)
        }
    }

    LaunchedEffect("InquiryListDetail") {
        getInquiryListDetail(
            inquiryViewModel = inquiryViewModel,
            notificationViewModel = notificationViewModel,
            onInquiryListDetailClick = { onInquiryListDetailClick() }
        )
    }

    LaunchedEffect("Answer") {
        getAnswer(viewModel = notificationViewModel)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 65.dp)
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
            viewModel = inquiryViewModel,
            onItemClick = { id ->
                inquiryViewModel.getInquiryListDetail(id = id)
            }
        )
    }
}

suspend fun getInquiryList(
    viewModel: InquiryViewModel
) {
    viewModel.getInquiryListResponse.collect {
        if (it is Event.Success) {
            viewModel.saveInquiryList(it.data!!.inquiryList)
        }
    }
}

suspend fun getInquiryListAll(
    viewModel: InquiryViewModel
) {
    viewModel.getInquiryListAllResponse.collect {
        if (it is Event.Success) {
            viewModel.saveInquiryList(it.data!!.inquiryList)
        }
    }
}

suspend fun getInquiryListDetail(
    inquiryViewModel: InquiryViewModel,
    notificationViewModel: NotificationViewModel,
    onInquiryListDetailClick: () -> Unit
) {
    inquiryViewModel.getInquiryListDetailResponse.collect {
        if (it is Event.Success) {
            inquiryViewModel.saveInquiryListDetail(it.data!!)
            if (inquiryViewModel.inquiryListDetail.value.inquiryStatus == "COMPLETE") {
                notificationViewModel.getAnswer(id = inquiryViewModel.inquiryListDetail.value.id)
            }
            onInquiryListDetailClick()
            inquiryViewModel.initInquiryListDetail()
        }
    }
}

suspend fun getAnswer(
    viewModel: NotificationViewModel
) {
    viewModel.getAnswerResponse.collect {
        if (it is Event.Success) {
            viewModel.saveAnswer(it.data!!)
        }
    }
}