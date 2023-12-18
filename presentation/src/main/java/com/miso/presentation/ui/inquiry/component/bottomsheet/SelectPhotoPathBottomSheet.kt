package com.miso.presentation.ui.inquiry.component.bottomsheet

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.miso.presentation.ui.util.PermissionHandlerActions

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun SelectPhotoPathBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedImageUri: (uri: Uri) -> Unit,
    onCameraClick: () -> Unit
) {
    val showPermissionDialog = remember { mutableStateOf(false) }

    val isCamera = remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                selectedImageUri(uri)
            }
        }

    val permissionsList = listOfNotNull(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    )
    val permissionState = rememberMultiplePermissionsState(permissions = permissionsList)

    SelectPhotoPathBottomSheetComponent(
        bottomSheetState = bottomSheetState,
        onGalleryLaunchButtonClick = {
            isCamera.value = false
            if(!showPermissionDialog.value && permissionState.permissions[0].status.isGranted) {
                galleryLauncher.launch("image/*")
            } else {
                showPermissionDialog.value = true
            }
        },
        onCameraLaunchButtonClick = {
            isCamera.value = true

            onCameraClick()
        }
    )

    if (!isCamera.value && permissionState.permissions[0].status.isGranted) {
        galleryLauncher.launch("image/*")
    } else if(!isCamera.value){
        PermissionHandlerActions(
            permissionState = permissionState,
            showPermissionDialog = showPermissionDialog,
            context = LocalContext.current
        )
    }
}