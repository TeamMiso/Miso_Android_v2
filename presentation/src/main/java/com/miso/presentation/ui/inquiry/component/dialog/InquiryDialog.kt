package com.miso.presentation.ui.inquiry.component.dialog

import androidx.compose.runtime.Composable
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.domain.repository.InquiryRepository
import com.miso.presentation.ui.inquiry.screen.inquiry
import com.miso.presentation.ui.inquiry.util.getMultipartFile
import kotlinx.coroutines.launch

@Composable
fun InquiryDialog(
    openDialog: Boolean,
    onDismissClick: () -> Unit,
    onCheckClick: () -> Unit,
    title: String,
    content: String,
    dismissText: String,
    checkText: String,
) {
    MisoDialog(
        openDialog = openDialog,
        onStateChange = {},
        title = title,
        content = content,
        dismissText = dismissText,
        checkText = checkText,
        onDismissClick = { onDismissClick() },
        onCheckClick = { onCheckClick() }
    )
}

