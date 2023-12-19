package com.miso.presentation.ui.shop.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.shop.component.PurchaseList
import com.miso.presentation.viewmodel.PurchaseViewModel
import com.miso.presentation.viewmodel.ShopViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun PurchaseListScreen(
    viewModel: PurchaseViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect("PurchaseList") {
        viewModel.getPurchaseList()
        purchaseList(viewModel = viewModel)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            MisoBackButton {
                onBackClick()
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                MisoBlackTitleText(text = "구매 내역")
                Spacer(modifier = Modifier.height(16.dp))
                PurchaseList(
                    viewModel = viewModel,
                    onItemClick = {}
                )
            }
        }
    }
}

suspend fun purchaseList(
    viewModel: PurchaseViewModel
) {
    viewModel.getPurchaseListResponse.collect {
        if (it is Event.Success) {
            viewModel.savePurchaseList(it.data!!.purchaseList)
        }
    }
}