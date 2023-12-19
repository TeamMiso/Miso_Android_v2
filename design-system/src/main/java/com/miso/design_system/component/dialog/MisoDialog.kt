package com.miso.design_system.component.dialog

import androidx.compose.runtime.Composable
import com.miso.design_system.theme.MisoTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MisoDialog(
    openDialog: Boolean,
    onStateChange: (Boolean) -> Unit,
    isRed: Boolean = false,
    title: String,
    content: String,
    dismissText: String,
    checkText: String,
    onDismissClick: () -> Unit,
    onCheckClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(openDialog) }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            MisoTheme { colors, typography ->
                Card(
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .width(328.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.background(colors.DIALOG_BG)) {
                        Column(
                            modifier = Modifier
                            .background(colors.DIALOG_BG)
                            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                        ) {
                            Text(
                                text = title,
                                modifier = Modifier.fillMaxWidth(),
                                color = colors.BLACK,
                                style = typography.textLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = content,
                                modifier = Modifier.fillMaxWidth(),
                                color = colors.GREYSCALE2,
                                style = typography.textSmall,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(end = 8.dp)
                                .background(colors.DIALOG_BG),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                modifier = Modifier.height(48.dp),
                                onClick = {
                                    openDialog = false
                                    onDismissClick()
                                }
                            ) {
                                Text(
                                    text = dismissText,
                                    color = colors.GREYSCALE2,
                                    style = typography.buttonLarge,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                            TextButton(
                                modifier = Modifier.height(48.dp),
                                onClick = {
                                    openDialog = false
                                    onCheckClick()
                                }
                            ) {
                                Text(
                                    text = checkText,
                                    color = if (!isRed) colors.PRIMARY else colors.RED1,
                                    style = typography.buttonLarge,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    else {
        onStateChange(openDialog)
    }
}

@Preview(showBackground = true)
@Composable
fun MisoDialogPreview() {
    MisoDialog(
        openDialog = true,
        onStateChange = {},
        title = "상품 구매하기",
        content = "종이뽑기를 구매하실 건가요?\n" +
                "100 Point가 소비됩니다.",
        dismissText = "취소",
        checkText = "구매",
        onDismissClick = {},
        onCheckClick = {}
    )
}