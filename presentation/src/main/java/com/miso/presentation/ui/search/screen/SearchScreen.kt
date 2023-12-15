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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.chip.MisoChip
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.design_system.component.textfield.MisoSearchTextField
import com.miso.presentation.ui.search.component.SearchHistoryList
import com.miso.presentation.ui.search.component.SearchHistoryTitleText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipComponent
import com.miso.presentation.ui.util.keyboardAsState

@Composable
fun SearchScreen(
    focusManager: FocusManager,
) {
    val isKeyboardOpen by keyboardAsState()

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var search by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, )
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

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            MisoSearchTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeHolder = "재활용 쓰레기 검색하기...",
                setText = search,
                onValueChange = { searchChange ->
                    search = searchChange
                },
                onClickButton = {},
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchHistoryTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            SearchHistoryList(
                searchHistoryList = listOf("test1", "test2", "test3")
            )
        }
        TodayEnvironmentTipComponent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 96.dp),
        ) {}
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreView() {
    SearchScreen(
        focusManager = LocalFocusManager.current,
    )
}