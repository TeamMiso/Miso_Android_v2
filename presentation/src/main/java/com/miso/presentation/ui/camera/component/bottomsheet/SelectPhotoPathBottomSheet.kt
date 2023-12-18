package com.miso.presentation.ui.camera.component.bottomsheet

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectPhotoPathBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedImageUri: (uri: Uri) -> Unit,
    onCameraClick: () -> Unit
) {
    val isCamera = remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                selectedImageUri(uri)
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted && isCamera.value -> {
                onCameraClick()

            }
            isGranted && !isCamera.value -> {
                galleryLauncher.launch("image/*")
            }
        }
    }
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    SelectPhotoPathBottomSheetComponent(
        bottomSheetState = bottomSheetState,
        onGalleryLaunchButtonClick = {
            isCamera.value = false
            permissionLauncher.launch(permission)
        },
        onCameraLaunchButtonClick = {
            isCamera.value = true
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    )
}