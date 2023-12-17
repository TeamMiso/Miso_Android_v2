package com.miso.presentation.ui.result.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.result.component.RecycleCautionText
import com.miso.presentation.ui.result.component.RecycleContentText
import com.miso.presentation.ui.result.component.RecycleImage
import com.miso.presentation.ui.result.component.RecycleMethodText
import com.miso.presentation.ui.result.component.RecycleTipText
import com.miso.presentation.ui.result.component.ResultRecyclablesTypeText
import com.miso.presentation.ui.result.component.ResultSubTitleText
import com.miso.presentation.ui.result.component.ResultTitleText

@Composable
fun ResultScreen(
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            MisoBackButton {
                onBackClick()
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                MisoBlackTitleText(text = "분리수거 이름")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        RecycleImage(imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfJ2Eht_sjF0lpVT5Cnxv-ERrIzEZ_dQZwqw&usqp=CAU")
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            ResultTitleText()
            Spacer(modifier = Modifier.height(16.dp))
            ResultSubTitleText(text = "#생수병 #맥주병")
            Spacer(modifier = Modifier.height(8.dp))
            ResultRecyclablesTypeText(text = "PET", imageUrl = "")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleMethodText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText("가나다\n\n라마바사\n\n아자차카\n\n타바하")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleTipText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText("가나\n\n다라마바\n\n사아자차\n\n카타바하")
            Spacer(modifier = Modifier.height(16.dp))
            RecycleCautionText()
            Spacer(modifier = Modifier.height(16.dp))
            RecycleContentText("가나다\n\n라마바\n\n사아자차카\n\n타바하")
            Spacer(modifier = Modifier.height(56.dp))
            MisoButton(
                modifier = Modifier,
                text = "10 포인트 받기"
            ) {}
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreView() {
    ResultScreen(
        onBackClick = {},
    )
}