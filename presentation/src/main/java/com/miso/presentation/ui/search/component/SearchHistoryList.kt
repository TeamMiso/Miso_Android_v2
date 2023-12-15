package com.miso.presentation.ui.search.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun SearchHistoryList(
    searchHistoryList: List<String>
) {
    MisoTheme { colors, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(searchHistoryList.size) { index ->
                val reversedIndex = searchHistoryList.size - 1 - index
                val historyItem = searchHistoryList[reversedIndex]
                SearchHistoryListItem(
                    title = historyItem,
                    content = historyItem,
                    image = "",
                    onItemClick = {}
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}