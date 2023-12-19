package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryList(
    list: List<String>,
    onItemClick: () -> Unit
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list.size) { index ->
                val listItem = list[index]
                InquiryListItem(
                    title = listItem,
                    date = "24.01.01",
                    status = "WAIT",
                    imageUrl = "",
                    onItemClick = { onItemClick() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}