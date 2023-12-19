package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryListDetailContentText
import com.miso.presentation.ui.inquiry.component.InquiryListDetailContentTitleText
import com.miso.presentation.ui.inquiry.component.InquiryListDetailImage
import com.miso.presentation.ui.inquiry.component.InquiryListDetailStatusText

@Composable
fun InquiryListDetailScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    MisoTheme { colors, typography ->
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
                    MisoBlackTitleText(text = "실패하면 반역, 성공하면 혁명 아닙니까!")
                    Spacer(modifier = Modifier.height(8.dp))
                    InquiryListDetailStatusText(date = "24.01.01", status = "WAIT")
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            InquiryListDetailImage(imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg")
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                InquiryListDetailContentTitleText(text = "문의 내용")
                Spacer(modifier = Modifier.height(16.dp))
                InquiryListDetailContentText(
                    text = "와~ 희철이! 내 동생 도희철이~\n" +
                            ">> 와 이라십니까 형님?\n" +
                            "니가 직접 가라! 응? 니가 가서 우리 2공수 아들 모조리 델고 온나.\n" +
                            ">> 뭐랍니까?\n" +
                            "웃어? 이 새끼 봐라…\n" +
                            ">> 잠깐만 잠깐만, 야 야 야 어어어!\n" +
                            "놔 봐라 쫌! 안 쏜다! 야 도희철 일루 와, 앞으로 와 이 스끼야! 총 잡아. 총 잡아! 니가 가기 싫으모 내 심장에다가 팍 쏴 삐라. 쏘라고!"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    modifier = Modifier.height(1.dp),
                    color = colors.GREYSCALE3
                )
                Spacer(modifier = Modifier.height(16.dp))
                InquiryListDetailContentTitleText(text = "문의에 대한 답변")
                Spacer(modifier = Modifier.height(16.dp))
                InquiryListDetailContentText(
                    text = "와~ 희철이! 내 동생 도희철이~\n" +
                            ">> 와 이라십니까 형님?\n" +
                            "니가 직접 가라! 응? 니가 가서 우리 2공수 아들 모조리 델고 온나.\n" +
                            ">> 뭐랍니까?\n" +
                            "웃어? 이 새끼 봐라…\n" +
                            ">> 잠깐만 잠깐만, 야 야 야 어어어!\n" +
                            "놔 봐라 쫌! 안 쏜다! 야 도희철 일루 와, 앞으로 와 이 스끼야! 총 잡아. 총 잡아! 니가 가기 싫으모 내 심장에다가 팍 쏴 삐라. 쏘라고!"
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}