package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.miso.presentation.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InquiryListScreen(
    state: String,
    lifecycleScope: CoroutineScope,
    inquiryViewModel: InquiryViewModel,
    notificationViewModel: NotificationViewModel,
    onInquiryClick: () -> Unit,
    onInquiryListDetailClick: () -> Unit,
    onBottomSheetClick: () -> Unit
) {
    val isRefreshing by inquiryViewModel.isRefreshing.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = {
                lifecycleScope.launch {
                    when (state) {
                        "ALL" -> {
                            inquiryViewModel.getInquiryList()
                            getInquiryList(viewModel = inquiryViewModel)
                        }

                        "WAIT", "COMPLETE" -> {
                            inquiryViewModel.getInquiryListFilter(state)
                            getInquiryListFilter(viewModel = inquiryViewModel)
                        }

                        else -> {
                            inquiryViewModel.inquiryList.clear()
                        }
                    }
                }
            }
        )

    LaunchedEffect(state) {
        when (state) {
            "ALL" -> {
                inquiryViewModel.getInquiryList()
                getInquiryList(viewModel = inquiryViewModel)
            }

            "WAIT", "COMPLETE" -> {
                inquiryViewModel.getInquiryListFilter(state)
                getInquiryListFilter(viewModel = inquiryViewModel)
            }

            else -> {
                inquiryViewModel.inquiryList.clear()
            }
        }
    }

    LaunchedEffect("InquiryListDetail") {
        getInquiryListDetail(
            inquiryViewModel = inquiryViewModel,
            notificationViewModel = notificationViewModel
        )
    }

    LaunchedEffect("Answer") {
        getAnswer(viewModel = notificationViewModel)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 65.dp)
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )
        Column(
            modifier = Modifier
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InquiryListTitleText()
                MisoChip(
                    text = "필터",
                    icon = R.drawable.ic_filter
                ) {
                    onBottomSheetClick()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            InquiryList(
                viewModel = inquiryViewModel,
                onItemClick = { id ->
                    inquiryViewModel.getInquiryListDetail(id = id)
                    onInquiryListDetailClick()
                }
            )
        }
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

suspend fun getInquiryListDetail(
    inquiryViewModel: InquiryViewModel,
    notificationViewModel: NotificationViewModel,
) {
    inquiryViewModel.getInquiryListDetailResponse.collect {
        if (it is Event.Success) {
            inquiryViewModel.saveInquiryListDetail(it.data!!)
            if (inquiryViewModel.inquiryListDetail.value.inquiryStatus == "COMPLETE") {
                notificationViewModel.getAnswer(id = inquiryViewModel.inquiryListDetail.value.id)
            }
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

suspend fun getInquiryListFilter(
    viewModel: InquiryViewModel
) {
    viewModel.getInquiryListFilterResponse.collect {
        if (it is Event.Success) {
            viewModel.saveInquiryList(it.data!!.inquiryList)
        }
    }
}