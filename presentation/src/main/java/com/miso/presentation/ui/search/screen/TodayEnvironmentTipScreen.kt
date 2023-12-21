package com.miso.presentation.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipContentText
import com.miso.presentation.ui.search.component.TodayEnvironmentTipImage
import com.miso.presentation.ui.search.component.TodayEnvironmentTipTitleText

@Composable
fun TodayEnvironmentTipScreen(
    onBackClick: () -> Unit
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
                MisoBlackTitleText(text = "오늘의 환경 지식")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        TodayEnvironmentTipImage(imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzBaTQnuPOBio0ol8_0X9ol6XbQGUQIczRRg&usqp=CAU")
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TodayEnvironmentTipTitleText(text = "프링글스 통은 어떻게 분류배출할 수 있을까요?")
            Spacer(modifier = Modifier.height(8.dp))
            TodayEnvironmentTipContentText(text = "프링글스 통을 보면 많은 재질이 혼합되어 있는데요,\n" +
                    "과연 프링글스 통은 분류 배출할 수 있을까요?\n" +
                    "공식적으로는 재활용 불가능합니다.\n" +
                    "때문에 제품에도 분류 배출 기호가 따로 없어요.\n" +
                    "하지만 아래 방법으로 분류 배출할 수 있어요!\n" +
                    "\n" +
                    "분류 배출 방법\n" +
                    "뚜껑: 플라스틱\n" +
                    "본체: 혼합 종이 (일반쓰레기)\n" +
                    "밑면: 알루미늄\n" +
                    "\n" +
                    "분류 배출을 위해서는 본체에서\n" +
                    "알루미늄 밑면을 칼로 말끔하게 도려낸 후,\n" +
                    "뚜껑은 플라스틱, 밑면은 캔류로 분리 배출해요.\n" +
                    "\n" +
                    "본체는 안쪽이 비닐 코팅된 혼합 종이이기 때문에\n" +
                    "종이로 재활용이 불가능해요. 일반쓰레기 (종량제봉투)\n" +
                    "로 버려주세요.\n" +
                    "\n" +
                    "주의할 점\n" +
                    "프링글스 통째로 종이로 분리 배출하는 경우가 많은데,\n" +
                    "종이로 배출할 경우 분리 수거 업체에서 선별 작업에\n" +
                    "어려움을 겪을 수 있어요.\n" +
                    "재질별로 분리하는 것이 어려운 경우 모두 일반쓰레기\n" +
                    "(종량제봉투)로 버려주세요.")
        }
    }
}