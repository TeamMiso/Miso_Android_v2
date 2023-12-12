package com.miso.presentation.ui.sign_up.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun NumberTextField(
    text: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    onFourCharactersEntered: () -> Unit
) {
    MisoTheme { colors, typography ->
        Column {
            BasicTextField(
                value = text.take(4),
                onValueChange = {
                    if (it.length <= 4) {
                        onValueChange(it)
                        if (it.length == 4) {
                            onFourCharactersEntered()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        text.forEachIndexed { index, char ->
                            NumberTextFieldCharContainer(
                                modifier = Modifier.weight(1f),
                                text = char,
                                isError = isError
                            )
                        }
                        repeat(4 - text.length) {
                            NumberTextFieldCharContainer(
                                modifier = Modifier.weight(1f),
                                text = ' ',
                                isError = isError
                            )
                        }
                    }
                },
                singleLine = true
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "인증번호가 틀렸습니다.",
                    color = colors.RED1,
                    style = typography.captionLarge
                )
            }
        }
    }
}

@Composable
private fun NumberTextFieldCharContainer(
    modifier: Modifier,
    text: Char,
    isError: Boolean,
) {
    val shape = remember { RoundedCornerShape(8.dp) }

    MisoTheme { colors, typography ->
        Box(
            modifier = modifier
                .size(
                    width = 70.dp,
                    height = 96.dp,
                )
                .background(
                    color = colors.GREYSCALE5,
                    shape = shape,
                )
                .border(
                    width = 1.dp,
                    color = if (!isError) Color.Transparent else colors.RED1,
                    shape = shape
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text.toString(),
                color = colors.PRIMARY,
                style = typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmailTextFieldPreView() {
    NumberTextField(
        text = "1234",
        isError = true,
        onValueChange = {},
        onFourCharactersEntered = {}
    )
}