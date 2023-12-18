package com.miso.presentation.ui.inquiry.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.inquiry.component.InquiryImageButton
import com.miso.presentation.ui.inquiry.component.InquiryTextTextField
import com.miso.presentation.ui.inquiry.component.InquiryTitleTextField
import com.miso.presentation.ui.inquiry.component.InquiryTopBar

@Composable
fun InquiryScreen() {
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            InquiryTopBar(onInquiryClick = {}, onBackClick = {})
            InquiryTitleTextField()
            InquiryImageButton(onClick = {}, selectedImageUri = Uri.EMPTY, capturedImage = null)
            InquiryTextTextField()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryScreenPreview() {
    InquiryScreen()
}