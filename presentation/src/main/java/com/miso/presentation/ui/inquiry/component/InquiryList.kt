package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.util.toDateString
import com.miso.presentation.viewmodel.InquiryViewModel

@Composable
fun InquiryList(
    viewModel: InquiryViewModel,
    onItemClick: (id: Long) -> Unit
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.inquiryList.size) { index ->
                val listItem = viewModel.inquiryList[index]
                InquiryListItem(
                    title = listItem.title,
                    date = listItem.inquiryDate.toDateString(),
                    status = listItem.inquiryStatus,
                    imageUrl = listItem.imageUrl ?: "",
                    onItemClick = { onItemClick(listItem.id) }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}