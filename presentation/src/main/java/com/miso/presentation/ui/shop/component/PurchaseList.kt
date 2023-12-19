package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun PurchaseList(
    list: List<String>,
    onItemClick: () -> Unit
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list.size) { index ->
                val listItem = list[index]
                PurchaseListItem(
                    title = listItem,
                    date = listItem,
                    imageUrl = listItem,
                    onItemClick = { onItemClick() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}