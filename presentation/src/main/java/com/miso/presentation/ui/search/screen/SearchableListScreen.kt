package com.miso.presentation.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.presentation.viewmodel.util.Event
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.search.component.SearchList
import com.miso.presentation.viewmodel.RecyclablesViewModel

@Composable
fun SearchableListScreen(
    viewModel: RecyclablesViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect("SearchableList") {
        searchableList(viewModel = viewModel)
    }

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
                isSearchHistory = false,
                viewModel = viewModel,
                onItemClick = { type ->
                    viewModel.result(type)
                }
            )
        }
    }
}

suspend fun searchableList(
    viewModel: RecyclablesViewModel,
) {
    viewModel.searchableListResponse.collect {
        if (it is Event.Success) {
            viewModel.saveSearchableList(it.data!!.recyclablesList)
        }
    }
}