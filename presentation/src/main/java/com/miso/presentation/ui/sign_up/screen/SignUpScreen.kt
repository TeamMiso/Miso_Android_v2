package com.miso.presentation.ui.sign_up.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.MisoBackButton
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.text.MisoBlackTitleText
import com.miso.design_system.component.textfield.MisoPasswordTextField
import com.miso.design_system.component.textfield.MisoTextField
import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.presentation.ui.util.isEmailValid
import com.miso.presentation.ui.util.keyboardAsState
import com.miso.presentation.viewmodel.AuthViewModel
import com.miso.presentation.viewmodel.util.Event

@Composable
fun SignUpScreen(
    focusManager: FocusManager,
    onBackClick: () -> Unit,
    viewModel: AuthViewModel,
    onVerificationClick: () -> Unit,
    onSignUpClick: (body: AuthSignUpRequestModel) -> Unit
) {
    val context = LocalContext.current
    val isKeyboardOpen by keyboardAsState()

    var sizeState by remember { mutableStateOf(0.112f) }
    var sizeState2 by remember { mutableStateOf(0.1f) }
    val animatedSpacerHeight by animateFloatAsState(targetValue = sizeState)
    val animatedSpacerHeight2 by animateFloatAsState(targetValue = sizeState)

    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
            sizeState = 0.03f
            sizeState2 = 0.05f
        } else {
            sizeState = 0.112f
            sizeState2 = 0.1f
            focusManager.clearFocus()
        }
    }

    LaunchedEffect("SignUp") {
        signUp(
            context = context,
            viewModel = viewModel,
            onSuccess = {
                onVerificationClick()
            }
        )
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    isPasswordError = if (password.isEmpty() || confirmPassword.isEmpty()) false
    else password != confirmPassword

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
            MisoBlackTitleText(text = "회원가입")
            Spacer(modifier = Modifier.fillMaxHeight(animatedSpacerHeight))
            MisoTextField(
                modifier = Modifier.fillMaxWidth(),
                titleText = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeHolder = "이메일",
                setText = email,
                isError = isEmailError,
                errorText = "이메일 형식이 올바르지 않습니다.",
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
                isLink = false,
                onValueChange = { passwordChange ->
                    password = passwordChange
                },
                singleLine = true
            )
            MisoTextField(
                modifier = Modifier.fillMaxWidth(),
                titleText = "Confirm Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolder = "비밀번호 확인",
                setText = confirmPassword,
                isError = isPasswordError,
                errorText = "비밀번호가 일치하지 않습니다",
                onValueChange = { confirmPasswordChange ->
                    confirmPassword = confirmPasswordChange
                },
                onClickButton = {
                    confirmPassword = ""
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.fillMaxHeight(animatedSpacerHeight2))
            MisoButton(
                text = "회원가입",
            ) {
                isEmailError = !email.isEmailValid()

                if (!isEmailError && !isPasswordError) {
                    onSignUpClick(
                        AuthSignUpRequestModel(
                            email = email,
                            password = password,
                            passwordCheck = confirmPassword
                        )
                    )
                }
            }
        }
    }
}

suspend fun signUp(
    context: Context,
    viewModel: AuthViewModel,
    onSuccess: () -> Unit,
) {
    viewModel.authSignUpResponse.collect {
        when (it) {
            is Event.Loading -> Unit

            is Event.Success -> {
                onSuccess()
            }

            is Event.Conflict -> {
                Toast.makeText(context, "이미 가입된 이메일 입니다.", Toast.LENGTH_LONG).show()
            }

            else -> {
                Toast.makeText(context, "알 수 없는 에러.", Toast.LENGTH_LONG).show()
            }
        }
    }
}