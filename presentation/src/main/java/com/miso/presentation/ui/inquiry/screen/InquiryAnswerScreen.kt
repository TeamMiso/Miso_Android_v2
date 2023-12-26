package com.miso.presentation.ui.inquiry.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.design_system.theme.MisoTheme
import com.miso.domain.model.inquiry.request.AnswerRequestModel
import com.miso.presentation.ui.inquiry.component.InquiryContentTextField
import com.miso.presentation.ui.inquiry.component.InquiryDateText
import com.miso.presentation.ui.inquiry.component.InquiryTitleText
import com.miso.presentation.ui.inquiry.component.InquiryTopBar
import com.miso.presentation.ui.util.keyboardAsState
import com.miso.presentation.ui.util.toDateString
import com.miso.presentation.viewmodel.InquiryViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun InquiryAnswerScreen(
    viewModel: InquiryViewModel,
    focusManager: FocusManager,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onInquiryListDetailClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    var openSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect("SendAnswer") {
        sendAnswer(
            viewModel = viewModel,
            onSuccess = {
                openSuccessDialog = true
            }
        )
    }

    val isKeyboardOpen by keyboardAsState()

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var content by remember { mutableStateOf("") }

    if (openDialog) {
        MisoDialog(
            openDialog = openDialog,
            onStateChange = {
                openDialog = it
            },
            title = "답변을 게시할까요?",
            content = content,
            dismissText = "취소",
            checkText = "답변 게시",
            onDismissClick = {},
            onCheckClick = {
                viewModel.sendAnswer(
                    id = viewModel.inquiryListDetail.value.id,
                    body = AnswerRequestModel(answer = content)
                )
            }
        )
    }

    if (openSuccessDialog) {
        MisoDialog(
            openDialog = openSuccessDialog,
            onStateChange = {
                openSuccessDialog = it
            },
            title = "답변 성공",
            content = "답변을 게시했어요.\n" +
                    "답변한 게시글을 보러 가시겠어요?",
            dismissText = "홈으로",
            checkText = "게시글로",
            onDismissClick = {
                viewModel.initSendAnswer()
                onSearchClick()
            },
            onCheckClick = {
                viewModel.initSendAnswer()
                onInquiryListDetailClick()
            }
        )
    }

    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .statusBarsPadding()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            InquiryTopBar(
                onInquiryClick = {
                    openDialog = true
                },
                onBackClick = {
                    onBackClick()
                },
                isUser = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InquiryTitleText(title = viewModel.inquiryListDetail.value.title)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                InquiryDateText(date = viewModel.inquiryListDetail.value.inquiryDate.toDateString())
            }
            InquiryContentTextField(
                content = content,
                onValueChange = {
                    content = it
                }
            )
        }
    }
}

suspend fun sendAnswer(
    viewModel: InquiryViewModel,
    onSuccess: () -> Unit,
) {
    viewModel.sendAnswerResponse.collect {
        if (it is Event.Success) {
            onSuccess()
        }
    }
}