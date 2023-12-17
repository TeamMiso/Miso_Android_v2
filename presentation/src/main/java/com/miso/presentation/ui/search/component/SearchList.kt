package com.miso.presentation.ui.search.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme
import com.miso.domain.model.recyclables.response.SearchableListModel
import com.miso.presentation.viewmodel.RecyclablesViewModel

@Composable
fun SearchList(
    isSearchHistory: Boolean,
    viewModel: RecyclablesViewModel,
    onItemClick: (type: String) -> Unit
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (isSearchHistory) {
                items(viewModel.searchHistory.size) { index ->
                    val reversedIndex = viewModel.searchHistory.size - 1 - index
                    val listItem = viewModel.searchHistory[reversedIndex]
                    SearchListItem(
                        title = listItem.title,
                        content = listItem.recycleMethod,
                        image = listItem.imageUrl,
                        type = listItem.recyclablesType,
                        onItemClick = { onItemClick(listItem.recyclablesType) }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            } else {
                items(viewModel.recyclableList.size) { index ->
                    val listItem = viewModel.recyclableList[index]
                    SearchListItem(
                        title = listItem.title,
                        content = listItem.recycleMethod,
                        image = listItem.imageUrl,
                        type = listItem.recyclablesType,
                        onItemClick = { onItemClick(listItem.recyclablesType) }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}