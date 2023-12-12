package com.miso.presentation.ui.login.screen

import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.textfield.MisoPasswordTextField
import com.miso.design_system.component.textfield.MisoTextField
import com.miso.presentation.ui.login.component.LoginDividerAndText
import com.miso.presentation.ui.login.component.MisoSubTitleText
import com.miso.presentation.ui.login.component.MisoTitleText
import com.miso.presentation.ui.login.component.MoveSignUpText
import com.miso.presentation.ui.util.keyboardAsState

@Composable
fun LoginScreen(
    focusManager: FocusManager,
    onSignUpClick: () -> Unit
) {
    val isKeyboardOpen by keyboardAsState()

    var sizeState by remember { mutableStateOf(0.2f) }
    val animatedSpacerHeight by animateFloatAsState(targetValue = sizeState)

    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
            sizeState = 0.1f
        } else {
            sizeState = 0.2f
            focusManager.clearFocus()
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(animatedSpacerHeight))
        MisoTitleText()
        Spacer(modifier = Modifier.height(8.dp))
        MisoSubTitleText()
        Spacer(modifier = Modifier.height(40.dp))
        MisoTextField(
            modifier = Modifier.fillMaxWidth(),
            titleText = "Email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeHolder = "이메일",
            setText = email,
            isError = false,
            onValueChange = { emailChange ->
                email = emailChange
            },
            onClickButton = {
                email = ""
            },
            singleLine = true
        )
        MisoPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            titleText = "Password",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeHolder = "비밀번호",
            setText = password,
            isError = false,
            onValueChange = { passwordChange ->
                password = passwordChange
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(56.dp))
        MisoButton(
            modifier = Modifier,
            text = "로그인",
        ) {}
        Spacer(modifier = Modifier.height(80.dp))
        LoginDividerAndText()
        Spacer(modifier = Modifier.height(16.dp))
        MoveSignUpText {
            onSignUpClick()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreView() {
    LoginScreen(
        focusManager = LocalFocusManager.current,
        onSignUpClick = {}
    )
}