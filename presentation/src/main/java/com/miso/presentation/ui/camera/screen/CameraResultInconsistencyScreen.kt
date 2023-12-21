package com.miso.presentation.ui.camera.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.camera.component.CameraResultInconsistencyItem
import com.miso.presentation.ui.camera.component.CameraResultInconsistencyText
import com.miso.presentation.viewmodel.CameraViewModel

@Composable
fun CameraResultInconsistencyScreen(
    onBackClick: () -> Unit,
    onItemClick: () -> Unit,
    viewModel: CameraViewModel
) {
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            MisoBackButton(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            ) {
                onBackClick()
            }
            Spacer(modifier = Modifier.height(12.dp))
            CameraResultInconsistencyText()
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn {
                items(viewModel.aiList.size) {index ->
                    val listItem = viewModel.aiList[index]
                    CameraResultInconsistencyItem(
                        title = listItem.title,
                        content = listItem.recycleMethod,
                        imageUrl = listItem.imageUrl,
                        onClick = {
                            viewModel.setResult(index)
                            onItemClick()
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
