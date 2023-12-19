package com.miso.presentation.ui.inquiry.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.theme.MisoTheme

@Composable
fun InquiryTitleTextField(
    title: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    text = title
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
                textColor = colors.BLACK
            ),
            placeholder = {
                Text(
                    text = "문의 제목 쓰기",
                    style = typography.titleSmall,
                    fontWeight = FontWeight(800),
                    color = colors.GREYSCALE3
                )
            },
            textStyle = typography.inquiryTitle,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryTitleTextFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        InquiryTitleTextField("Miso", onValueChange = {})
    }
}