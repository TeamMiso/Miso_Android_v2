package com.miso.presentation.ui.setting.screen

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.dialog.MisoDialog
import com.miso.design_system.component.text.MisoLogoTitleText
import com.miso.design_system.component.toggle.MisoToggle
import com.miso.presentation.ui.setting.component.EmailText
import com.miso.presentation.ui.setting.component.LogoutButton
import com.miso.presentation.ui.setting.component.PushNotificationText
import com.miso.presentation.viewmodel.UserViewModel

@Composable
fun SettingScreen(
    context: Context,
    viewModel: UserViewModel,
    onLogoutClick: () -> Unit
) {
    var isToggle by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    LaunchedEffect("Notification") {
        isToggle = notificationManager.areNotificationsEnabled()
    }

    if (openDialog) {
        MisoDialog(
            openDialog = openDialog,
            onStateChange = {
                openDialog = it
            },
            isRed = true,
            title = "로그아웃",
            content = "로그아웃하시려고요?",
            dismissText = "취소",
            checkText = "로그아웃",
            onDismissClick = {},
            onCheckClick = { onLogoutClick() }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MisoLogoTitleText()
        }
        Spacer(modifier = Modifier.height(16.dp))
        EmailText(text = viewModel.userInfo.value.email)
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PushNotificationText()
            MisoToggle(selected = isToggle) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.data = Uri.fromParts("package", context.packageName, null)
                context.startActivity(intent)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        LogoutButton {
            openDialog = true
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}