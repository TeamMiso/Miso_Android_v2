package com.miso.presentation.ui.search.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.miso.viewmodel.util.Event
import com.miso.design_system.component.chip.MisoChip
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.design_system.component.textfield.MisoSearchTextField
import com.miso.presentation.ui.search.component.SearchList
import com.miso.presentation.ui.search.component.SearchListItem
import com.miso.presentation.ui.search.component.SearchHistoryTitleText
import com.miso.presentation.ui.search.component.SearchTitleText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipComponent
import com.miso.presentation.ui.util.keyboardAsState
import com.miso.presentation.viewmodel.RecyclablesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    focusManager: FocusManager,
    viewModel: RecyclablesViewModel,
    lifecycleScope: CoroutineScope,
    onSearchableListClick: () -> Unit,
    onResultClick: () -> Unit
) {
    val isKeyboardOpen by keyboardAsState()

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var search by remember { mutableStateOf("") }

    LaunchedEffect("Search") {
        search(viewModel = viewModel)
    }

    LaunchedEffect("Result") {
        result(viewModel = viewModel)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp,)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MisoLogoTitleText()
                MisoChip(
                    text = "검색 가능 목록",
                    icon = com.miso.design_system.R.drawable.ic_menu
                ) {
                    viewModel.searchableList()
                    onSearchableListClick()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            MisoSearchTextField(
                modifier = Modifier.fillMaxWidth(),
                debounceTime = 300L,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeHolder = "재활용 쓰레기 검색하기...",
                setText = search,
                onValueChange = { searchChange ->
                    search = searchChange
                },
                onSearchTextChange = {
                    lifecycleScope.launch {
                        if (it.isNotBlank()) {
                            viewModel.search(it)
                        }
                    }
                },
                onClickButton = {},
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (search.isEmpty()) SearchHistoryTitleText() else SearchTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            if (search.isEmpty()) {
                SearchList(
                    viewModel = viewModel
                )
            } else {
                SearchListItem(
                    title = viewModel.title.value,
                    content = viewModel.recycleMethod.value,
                    image = viewModel.imageUrl.value
                ) {
                    viewModel.result(viewModel.recyclablesType.value)
                    onResultClick()
                }
            }
        }
        TodayEnvironmentTipComponent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 96.dp),
        ) {}
    }
}

suspend fun search(
    viewModel: RecyclablesViewModel
) {
    viewModel.searchResponse.collect {
        if (it is Event.Success) {
            viewModel.saveSearch(it.data!!)
        }
    }
}

suspend fun result(
    viewModel: RecyclablesViewModel
) {
    viewModel.resultResponse.collect {
        if (it is Event.Success) {
            viewModel.saveResult(it.data!!)
        }
    }
}