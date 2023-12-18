package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.miso.presentation.viewmodel.ShopViewModel

@Composable
fun ShopList(
    viewModel: ShopViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(viewModel.shopList.size) { index ->
            val listItem = viewModel.shopList[index]
            ShopListItem(
                name = listItem.name,
                price = listItem.price,
                image = listItem.imageUrl,
                onItemClick = {}
            )
        }
    }
}