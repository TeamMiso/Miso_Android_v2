package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryContentTextField(
    content: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    text = content
    MisoTheme { colors, typography ->
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE),
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = colors.BLACK,
                cursorColor = colors.BULE1
            ),
            placeholder = {
                Text(
                    text = "문의 제목 쓰기",
                    style = typography.textSmall,
                    fontWeight = FontWeight(600),
                    color = colors.GREYSCALE3
                )
            },
            textStyle = typography.inquiryText,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryTextTextFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        InquiryContentTextField("Miso",{})
    }
}