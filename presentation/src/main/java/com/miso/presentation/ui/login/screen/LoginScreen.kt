package com.miso.presentation.ui.login.screen

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.viewmodel.util.Event
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.textfield.MisoPasswordTextField
import com.miso.design_system.component.textfield.MisoTextField
import com.miso.domain.model.auth.request.AuthLogInRequestModel
import com.miso.presentation.ui.login.component.LoginDividerAndText
import com.miso.presentation.ui.login.component.MisoSubTitleText
import com.miso.presentation.ui.login.component.MisoTitleText
import com.miso.presentation.ui.login.component.MoveSignUpText
import com.miso.presentation.ui.util.keyboardAsState
import com.miso.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun LoginScreen(
    focusManager: FocusManager,
    lifecycleScope: CoroutineScope,
    viewModel: AuthViewModel,
    onSignUpClick: () -> Unit,
    onLoginClick: (body: AuthLogInRequestModel) -> Unit
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

    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    var isButtonClick by remember { mutableStateOf(false) }

    LaunchedEffect(isButtonClick) {
        viewModel.initLogIn()
    }

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
            isError = isEmailError,
            errorText = errorText,
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
            isError = isPasswordError,
            errorText = errorText,
            onValueChange = { passwordChange ->
                password = passwordChange
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(56.dp))
        MisoButton(
            modifier = Modifier,
            text = "로그인",
        ) {
            isButtonClick = !isButtonClick
            if (email.isNotBlank() && password.isNotBlank()) {
                onLoginClick(
                    AuthLogInRequestModel(
                        email = email,
                        password = password
                    )
                )
                lifecycleScope.launch {
                    login(
                        viewModel = viewModel,
                        isEmailError = { error ->
                            isEmailError = error
                        },
                        isPasswordError = { error ->
                            isPasswordError = error
                        },
                        errorText = { text ->
                            errorText = text
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        LoginDividerAndText()
        Spacer(modifier = Modifier.height(16.dp))
        MoveSignUpText {
            onSignUpClick()
        }
    }
}

suspend fun login(
    viewModel: AuthViewModel,
    isEmailError: (error: Boolean) -> Unit,
    isPasswordError: (error: Boolean) -> Unit,
    errorText: (errorText: String) -> Unit,
) {
    viewModel.authLogInResponse.collect {
        when (it) {
            is Event.Loading -> {
                isEmailError(false)
                isPasswordError(false)
            }

            is Event.Success -> {
                viewModel.saveToken(token = it.data!!)
            }

            is Event.BadRequest -> {
                isPasswordError(true)
                errorText("비밀번호가 틀렸습니다.")
            }

            is Event.NotFound -> {
                isEmailError(true)
                errorText("가입되지 않은 이메일입니다.")
            }

            else -> {
                errorText("알 수 없는 에러")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreView() {
//    LoginScreen(
//        focusManager = LocalFocusManager.current,
//        lifecycleScope = CoroutineScope(Dispatchers.Main),
//        viewModel = null,
//        onSignUpClick = {},
//        onLoginClick = {}
//    )
}