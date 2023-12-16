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
    viewModel: RecyclablesViewModel
) {
    MisoTheme { _, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.recyclableList.size) { index ->
                val reversedIndex = viewModel.recyclableList.size - 1 - index
                val listItem = viewModel.recyclableList[reversedIndex]
                SearchListItem(
                    title = listItem.title,
                    content = listItem.recycleMethod,
                    image = listItem.imageUrl,
                    onItemClick = {}
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}