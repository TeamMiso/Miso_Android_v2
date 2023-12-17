package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable

@Composable
fun ShopList(
    list: List<String>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(list.size) { index ->
            val listItem = list[index]
            ShopListItem(
                name = listItem,
                price = listItem.toInt(),
                image = "https://m.positano.co.kr/web/product/big/202308/d8cf5f834420c3bb939af86e6ac189ab.jpeg",
                onItemClick = {}
            )
        }
    }
}