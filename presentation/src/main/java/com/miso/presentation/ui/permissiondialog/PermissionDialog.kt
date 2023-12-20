package com.miso.presentation.ui.permissiondialog

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .width(328.dp),
                elevation = 8.dp
            ) {
                Column(modifier = Modifier.background(colors.DIALOG_BG)) {
                    Column(
                        modifier = Modifier
                            .background(colors.DIALOG_BG)
                            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = permissionDescriptionProvider.getIcon(context)),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = permissionDescriptionProvider.getTitle(context = context),
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.BLACK,
                            style = typography.textLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = permissionDescriptionProvider.getDescription(context = context, isPermissionPermanentDenial = isPermissionPermanentDenial),
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
                                onDismiss()
                            }
                        ) {
                            Text(
                                text = "거부",
                                color = colors.GREYSCALE2,
                                style = typography.buttonLarge,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        TextButton(
                            modifier = Modifier.height(48.dp),
                            onClick = {
                                if (isPermissionPermanentDenial) {
                                    onGoToAppSettingsClick()
                                } else {
                                    onOkClick()
                                }
                            }
                        ) {
                            Text(
                                text = "승인",
                                color = colors.PRIMARY,
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

