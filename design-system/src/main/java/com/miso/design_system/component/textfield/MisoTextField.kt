package com.miso.design_system.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.icon.DeleteButtonIcon
import com.miso.design_system.icon.VisibilityIcon
import com.miso.design_system.icon.VisibilityOffIcon
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoTextField(
    titleText: String = "Email",
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onClickButton: () -> Unit,
) {
    val isFocused = remember {
        mutableStateOf(false)
    }

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus()
        }
    }

    MisoTheme { colors, typography ->
        Column(modifier = modifier) {
            Text(
                text = titleText,
                modifier = Modifier.padding(start = 8.dp),
                color = if (isError) colors.RED1
                else if (isFocused.value) colors.PRIMARY
                else colors.GREYSCALE3,
                style = typography.captionLarge,
                fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = setText,
                onValueChange = {
                    onValueChange(it)
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = typography.textSmall,
                        color = if (isError) colors.RED1 else colors.GREYSCALE3
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = if (isError) colors.RED1
                        else if (isFocused.value) colors.PRIMARY
                        else colors.GREYSCALE3,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                maxLines = maxLines,
                singleLine = singleLine,
                textStyle = typography.textSmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = if (isError) colors.RED1 else colors.BLACK,
                    backgroundColor = Color.Transparent,
                    placeholderColor = colors.GREYSCALE3,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.BULE1
                ),
                trailingIcon = {
                    IconButton(onClick = onClickButton) {
                        DeleteButtonIcon()
                    }
                },
                readOnly = readOnly
            )
            Column(
                modifier = Modifier.height(32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (isError) {
                    Text(text = errorText, color = colors.RED1, style = typography.captionLarge)
                }
            }
        }
    }
}

@Composable
fun MisoPasswordTextField(
    titleText: String = "Email",
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    isLink: Boolean = true,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onFindPasswordClick: () -> Unit = {}
) {
    val isFocused = remember { mutableStateOf(false) }

    var passwordVisibility by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus()
        }
    }

    MisoTheme { colors, typography ->
        Column(modifier = modifier) {
            Text(
                text = titleText,
                modifier = Modifier.padding(start = 8.dp),
                color = if (isError) colors.RED1
                else if (isFocused.value) colors.PRIMARY
                else colors.GREYSCALE3,
                style = typography.captionLarge,
                fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = setText,
                onValueChange = {
                    onValueChange(it)
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = typography.textSmall,
                        color = if (isError) colors.RED1 else colors.GREYSCALE3
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = if (isError) colors.RED1
                        else if (isFocused.value) colors.PRIMARY
                        else colors.GREYSCALE3,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                maxLines = maxLines,
                singleLine = singleLine,
                textStyle = typography.textSmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = if (isError) colors.RED1 else colors.BLACK,
                    backgroundColor = Color.Transparent,
                    placeholderColor = colors.GREYSCALE3,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.BULE1
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        if (passwordVisibility) VisibilityIcon() else VisibilityOffIcon()
                    }
                },
                readOnly = readOnly,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isError) {
                    Text(
                        text = errorText,
                        color = colors.RED1,
                        style = typography.captionLarge
                    )
                } else {
                    Text(
                        text = "비밀번호를 잊으셨나요?",
                        color = colors.GREYSCALE3,
                        style = typography.captionLarge
                    )
                }

                if (isLink) {
                    Text(
                        text = "비밀번호 찾기",
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onFindPasswordClick() },
                        color = colors.BULE1,
                        style = typography.captionLarge
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MisoTextFieldPreview() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MisoTextField(
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "Test",
            isError = true,
            onClickButton = {},
            onValueChange = {},
            setText = ""
        )

        MisoPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "Test",
            isError = true,
            onValueChange = {},
            setText = ""
        )
    }
}