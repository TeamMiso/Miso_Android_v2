package com.miso.presentation.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.search.component.SearchList

@Composable
fun SearchableListScreen(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        MisoBackButton {
            onBackClick()
        }
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            MisoBlackTitleText(text = "검색 가능한 항목")
            Spacer(modifier = Modifier.height(16.dp))
            SearchList(
                searchHistoryList = listOf("test1", "test2", "test3")
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchableListScreenPreView() {
    SearchableListScreen(
        onBackClick = {},
    )
}