package com.miso.presentation.ui.camera

import android.Manifest
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.camera.screen.CameraScreen
import com.miso.presentation.ui.util.PermissionHandlerActions

enum class CameraPage(val value: String) {
    Camera("Camera")
}

class CameraActivity : BaseActivity() {
    private lateinit var navController: NavController
    @OptIn(ExperimentalPermissionsApi::class)
    override fun init() {
        setContent {
            navController = rememberNavController()

            val showPermissionDialog = remember { mutableStateOf(false) }

            val permissionsList = listOfNotNull(
                Manifest.permission.CAMERA
            )
            val permissionState = rememberMultiplePermissionsState(permissions = permissionsList)

            LaunchedEffect(key1 = Unit) {
                if (!permissionState.permissions[0].status.isGranted && !permissionState.permissions[0].status.shouldShowRationale) run {
                    permissionState.permissions[0].launchPermissionRequest()
                }
            }
            NavHost(
                navController = navController as NavHostController,
                startDestination = CameraPage.Camera.value
            ) {
                composable(CameraPage.Camera.name) {
                    if (!permissionState.permissions[0].status.isGranted) {
                        showPermissionDialog.value = true
                        PermissionHandlerActions(
                            permissionState = permissionState,
                            showPermissionDialog = showPermissionDialog,
                            context = this@CameraActivity
                        )
                    }else {
                        showPermissionDialog.value = false
                        CameraScreen(
                            context = this@CameraActivity
                        )
                    }
                }
            }
        }
    }
}