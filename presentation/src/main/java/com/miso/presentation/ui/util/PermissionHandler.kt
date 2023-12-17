package com.miso.presentation.ui.util

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale
import com.miso.presentation.ui.permissiondialog.PermissionDialog


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandlerActions(
    permissionState: MultiplePermissionsState,
    showPermissionDialog: MutableState<Boolean>,
    context: Context
) {
    if (showPermissionDialog.value){
        CheckMultiplePermissions(
            permissionState = permissionState,
            onPermissionResult = { if (it) showPermissionDialog.value = false },
            showPermissionDialog = showPermissionDialog,
            context = context
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CheckMultiplePermissions(
    permissionState: MultiplePermissionsState,
    onPermissionResult: (Boolean) -> Unit,
    showPermissionDialog: MutableState<Boolean>,
    context: Context
) {
    val permissionDescriptionProviderMap = createPermissionMap()

    when {
        permissionState.allPermissionsGranted -> {
            onPermissionResult(true)
        }

        else -> {
            onPermissionResult(false)
            BlockedPermissionDialog(
                permissionState = permissionState,
                permissionDescriptionProviderMap = permissionDescriptionProviderMap,
                context = context,
                showPermissionDialog = showPermissionDialog
            )
        }
    }

}

private fun createPermissionMap() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    mapOf(
        Manifest.permission.CAMERA to CameraPermissionDescriptionProvider(),
        Manifest.permission.READ_MEDIA_IMAGES to ReadMediaImagesPermissionDescriptionProvider()
    )
} else {
    mapOf(
        Manifest.permission.CAMERA to CameraPermissionDescriptionProvider(),
        Manifest.permission.READ_EXTERNAL_STORAGE to ReadMediaImagesPermissionDescriptionProvider()
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun BlockedPermissionDialog(
    permissionState: MultiplePermissionsState,
    permissionDescriptionProviderMap: Map<String, PermissionDescriptionProvider>,
    context: Context,
    showPermissionDialog: MutableState<Boolean>
) {
    val blockedPermissionList = permissionState.revokedPermissions.lastOrNull()

    blockedPermissionList?.let {
        val descriptionProvider =
            permissionDescriptionProviderMap[it.permission] ?: return@let

        ShowPermissionDialog(
            permissionState,
            it.status,
            descriptionProvider,
            context,
            showPermissionDialog
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ShowPermissionDialog(
    permissionState: MultiplePermissionsState,
    permissionStatus: PermissionStatus,
    descriptionProvider: PermissionDescriptionProvider,
    context: Context,
    showPermissionDialog: MutableState<Boolean>,
) {
    PermissionDialog(
        permissionDescriptionProvider = descriptionProvider,
        isPermissionPermanentDenial = !permissionStatus.shouldShowRationale,
        onDismiss = { showPermissionDialog.value = false },
        onOkClick = { permissionState.launchMultiplePermissionRequest() },
        onGoToAppSettingsClick = {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent)
        },
        context = context
    )
}
