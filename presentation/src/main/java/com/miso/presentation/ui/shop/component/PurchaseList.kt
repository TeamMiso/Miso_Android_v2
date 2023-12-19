package com.miso.presentation.ui.shop.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.util.toDateString
import com.miso.presentation.viewmodel.PurchaseViewModel

@Composable
fun PurchaseList(
    viewModel: PurchaseViewModel,
    onItemClick: () -> Unit
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.purchaseList.size) { index ->
                val listItem = viewModel.purchaseList[index]
                PurchaseListItem(
                    title = listItem.name,
                    date = listItem.createdDate.toDateString(),
                    imageUrl = listItem.imageUrl,
                    onItemClick = { onItemClick() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}