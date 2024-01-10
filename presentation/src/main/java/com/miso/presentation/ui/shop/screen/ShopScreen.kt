package com.miso.presentation.ui.shop.screen

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
import com.miso.presentation.viewmodel.util.Event
import com.miso.design_system.R
import com.miso.design_system.component.chip.MisoChip
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.presentation.ui.shop.component.ShopList
import com.miso.presentation.viewmodel.ShopViewModel

@Composable
fun ShopScreen(
    viewModel: ShopViewModel,
    onShopDetailClick: () -> Unit,
    onPurchaseListClick: () -> Unit
) {
    LaunchedEffect("ShopList") {
        viewModel.shopList()
        shopList(viewModel = viewModel)
    }

    LaunchedEffect("ShopListDetail") {
        shopListDetail(
            viewModel = viewModel
        )
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
                text = "구매내역",
                icon = R.drawable.ic_purchase
            ) {
                onPurchaseListClick()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ShopList(
            viewModel = viewModel,
            onItemClick = { id ->
                viewModel.shopListDetail(id = id)
                onShopDetailClick()
            }
        )
    }
}

suspend fun shopList(
    viewModel: ShopViewModel
) {
    viewModel.shopListResponse.collect {
        if (it is Event.Success) {
            viewModel.saveShopList(it.data!!.itemList)
        }
    }
}

suspend fun shopListDetail(
    viewModel: ShopViewModel
) {
    viewModel.shopListDetailResponse.collect {
        if (it is Event.Success) {
            viewModel.saveShopListDetail(it.data!!)
        }
    }
}