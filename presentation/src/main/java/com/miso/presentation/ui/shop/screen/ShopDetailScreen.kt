package com.miso.presentation.ui.shop.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.shop.component.DetailContentText
import com.miso.presentation.ui.shop.component.DetailImage
import com.miso.presentation.ui.shop.component.UserPointText
import com.miso.presentation.ui.util.formatNumber
import com.miso.presentation.viewmodel.PurchaseViewModel
import com.miso.presentation.viewmodel.ShopViewModel
import com.miso.presentation.viewmodel.UserViewModel
import com.miso.presentation.viewmodel.util.Event

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShopDetailScreen(
    shopViewModel: ShopViewModel,
    userViewModel: UserViewModel,
    purchaseViewModel: PurchaseViewModel,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPurchaseListClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    var openSuccessDialog by remember { mutableStateOf(false) }
    var openFailDialog by remember { mutableStateOf(false) }


    LaunchedEffect("Purchase") {
        purchase(
            purchaseViewModel = purchaseViewModel,
            userViewModel = userViewModel,
            onSuccess = {
                openSuccessDialog = true
            },
            onFail = {
                openFailDialog = true
            }
        )
    }

    if (openDialog) {
        MisoDialog(
            openDialog = openDialog,
            onStateChange = {
                openDialog = it
            },
            title = "상품 구매하기",
            content = "${shopViewModel.shopListDetail.value.price}을 구매하실 건가요?\n" +
                    "${formatNumber(shopViewModel.shopListDetail.value.price)} Point가 소비됩니다.",
            dismissText = "취소",
            checkText = "구매",
            onDismissClick = {},
            onCheckClick = { purchaseViewModel.purchase(shopViewModel.shopListDetail.value.id) }
        )
    }

    if (openFailDialog) {
        MisoDialog(
            openDialog = openFailDialog,
            onStateChange = {
                openFailDialog = it
            },
            title = "상품 구매 불가",
            content = "보유하신 포인트가 부족해요.\n" +
                    "포인트를 획득하러 가시겠어요?",
            dismissText = "홈으로",
            checkText = "카메라로",
            onDismissClick = {
                purchaseViewModel.initPurchase()
                onSearchClick()
            },
            onCheckClick = {
                purchaseViewModel.initPurchase()
                onCameraClick()
            }
        )
    }

    if (openSuccessDialog) {
        MisoDialog(
            openDialog = openSuccessDialog,
            onStateChange = {
                openSuccessDialog = it
            },
            title = "상품 구매 완료",
            content = "구매가 완료되었어요.\n" +
                    "구매 내역으로 이동할까요?",
            dismissText = "홈으로",
            checkText = "내역 보기",
            onDismissClick = {
                purchaseViewModel.initPurchase()
                onSearchClick()
            },
            onCheckClick = {
                purchaseViewModel.initPurchase()
                onPurchaseListClick()
            }
        )
    }

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
                MisoBlackTitleText(text = shopViewModel.shopListDetail.value.name)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        DetailImage(imageUrl = shopViewModel.shopListDetail.value.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailContentText(text = shopViewModel.shopListDetail.value.content)
            Spacer(modifier = Modifier.weight(1f))
            MisoButton(
                modifier = Modifier,
                text = if (shopViewModel.shopListDetail.value.amount != 0) "${
                    formatNumber(
                        shopViewModel.shopListDetail.value.price
                    )
                } 포인트로 구매하기" else "품절되었습니다",
                enabled = shopViewModel.shopListDetail.value.amount != 0
            ) {
                openDialog = true
            }
            Spacer(modifier = Modifier.height(8.dp))
            UserPointText(text = "${formatNumber(userViewModel.userInfo.value.point)} 포인트")
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

suspend fun purchase(
    purchaseViewModel: PurchaseViewModel,
    userViewModel: UserViewModel,
    onSuccess: () -> Unit,
    onFail: () -> Unit
) {
    purchaseViewModel.purchaseResponse.collect {
        when (it) {
            is Event.Success -> {
                userViewModel.getPoint()
                onSuccess()
            }

            is Event.ForBidden -> {
                onFail()
            }

            else -> {}
        }
    }
}