package com.miso.presentation.ui.sign_up.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.presentation.ui.sign_up.component.NumberTextField
import com.miso.presentation.ui.util.keyboardAsState

@Composable
fun VerificationScreen(
    focusManager: FocusManager,
    onBackClick: () -> Unit
) {
    val isKeyboardOpen by keyboardAsState()

    var sizeState by remember { mutableStateOf(0.33f) }
    val animatedSpacerHeight by animateFloatAsState(targetValue = sizeState)

    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
            sizeState = 0.2f
        } else {
            sizeState = 0.33f
            focusManager.clearFocus()
        }
    }

    var number by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .statusBarsPadding()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        MisoBackButton {
            onBackClick()
        }
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            MisoBlackTitleText(text = "인증번호 입력")
            Spacer(modifier = Modifier.fillMaxHeight(animatedSpacerHeight))
            NumberTextField(
                text = number,
                isError = isError,
                onValueChange = {
                    number = it
                },
                onFourCharactersEntered = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun VerificationScreenPreView() {
    VerificationScreen(
        focusManager = LocalFocusManager.current,
        onBackClick = {}
    )
}