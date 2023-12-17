package com.miso.presentation.ui.permissiondialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.util.PermissionDescriptionProvider

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    context: Context,
    permissionDescriptionProvider: PermissionDescriptionProvider,
    isPermissionPermanentDenial: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        MisoTheme { colors, typography ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .size(400.dp, 300.dp)
                    .padding(top = 5.dp, bottom = 10.dp),
                elevation = 8.dp
            ) {
                Column(modifier = Modifier.background(colors.GREYSCALE1)) {
                    Column(modifier = Modifier
                        .background(colors.GREYSCALE1)
                        .padding(top = 16.dp, bottom = 20.dp)) {
                        Text(
                            text = permissionDescriptionProvider.getTitle(context = context),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.WHITE,
                            style = typography.textMedium,
                            fontWeight = FontWeight.Normal,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = permissionDescriptionProvider.getDescription(
                                context = context,
                                isPermissionPermanentDenial = isPermissionPermanentDenial
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.GREYSCALE2,
                            style = typography.titleSmall,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Divider(
                        color = colors.GREYSCALE2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(colors.GREYSCALE1),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Text(
                                "취소",
                                color = colors.WHITE,
                                style = typography.textMedium,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Divider(
                            color = colors.GREYSCALE2,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(0.5.dp)
                        )
                        TextButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = {
                                if (isPermissionPermanentDenial) {
                                    onGoToAppSettingsClick()
                                } else {
                                    onOkClick()
                                }
                            }
                        ) {
                            Text(
                                "권한 승인",
                                color = colors.BULE1,
                                style = typography.textMedium,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                }
            }
        }
    }
}