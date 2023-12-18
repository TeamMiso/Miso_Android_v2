package com.miso.presentation.ui.shop.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.shop.component.DetailContentText
import com.miso.presentation.ui.shop.component.DetailImage
import com.miso.presentation.ui.shop.component.UserPointText

@Composable
fun ShopDetailScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            MisoBackButton {
                onBackClick()
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                MisoBlackTitleText(text = "테스트")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        DetailImage(imageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg")
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailContentText(text = "안녕하세요")
            Spacer(modifier = Modifier.weight(1f))
            MisoButton(
                modifier = Modifier,
                text = "100 포인트로 구매하기"
            ) {}
            Spacer(modifier = Modifier.height(8.dp))
            UserPointText(text = "1000000000 포인트")
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}