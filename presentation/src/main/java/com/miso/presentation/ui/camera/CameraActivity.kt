package com.miso.presentation.ui.camera

import android.Manifest
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.miso.presentation.ui.camera.screen.CameraCaptureResultScreen
import com.miso.presentation.ui.camera.screen.CameraResultScreen
import com.miso.presentation.ui.camera.screen.CameraScreen
import com.miso.presentation.ui.search.SearchActivity
import com.miso.presentation.ui.util.PermissionHandlerActions
import com.miso.presentation.viewmodel.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint

enum class CameraPage(val value: String) {
    Camera("Camera"),
    CameraCaptureResult("CameraCaptureResult"),
    CameraResult("CameraResult")
}
@AndroidEntryPoint
class CameraActivity : BaseActivity() {
    private lateinit var navController: NavController

    private val cameraViewModel by viewModels<CameraViewModel>()
    @OptIn(ExperimentalPermissionsApi::class)
    override fun init() {
        setContent {
            navController = rememberNavController()

            cameraViewModel.isInquiry.value = intent.getBooleanExtra("isInquiry",false)

            val showPermissionDialog = remember { mutableStateOf(false) }

            val permissionsList = listOfNotNull(
                Manifest.permission.CAMERA
            )
            val permissionState = rememberMultiplePermissionsState(permissions = permissionsList)

            LaunchedEffect("Permission") {
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
                            context = this@CameraActivity,
                        )
                    } else {
                        showPermissionDialog.value = false
                        CameraScreen(
                            context = this@CameraActivity,
                            viewModel = cameraViewModel,
                            navController = navController,
                            onBackClick = {
                                val intent = Intent(
                                    this@CameraActivity,
                                    SearchActivity::class.java
                                )
                                startActivity(intent)
                                finish()
                            },
                            onInquiryCapture = {
                                val intent = Intent(
                                    this@CameraActivity,
                                    SearchActivity::class.java
                                )
                                intent.putExtra("isCamera",true)
                                intent.putExtra("byteArray",it)
                                startActivity(intent)
                                finish()
                            }
                        )
                    }
                }
                composable(CameraPage.CameraCaptureResult.name) {
                    CameraCaptureResultScreen(
                        viewModel = cameraViewModel,
                        navController = navController,
                        onSearch = {}
                    )
                }
                composable(CameraPage.CameraResult.name){
                    CameraResultScreen(
                        viewModel = cameraViewModel,
                        onBackClick = {}
                    )
                }
            }
        }
    }
}