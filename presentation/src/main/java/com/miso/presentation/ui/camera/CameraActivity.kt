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
import com.miso.presentation.ui.camera.screen.CameraResultInconsistencyScreen
import com.miso.presentation.ui.camera.screen.CameraResultScreen
import com.miso.presentation.ui.camera.screen.CameraScreen
import com.miso.presentation.ui.login.LoginActivity
import com.miso.presentation.ui.search.SearchActivity
import com.miso.presentation.ui.util.PermissionHandlerActions
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

enum class CameraPage(val value: String) {
    Camera("Camera"),
    CameraCaptureResult("CameraCaptureResult"),
    CameraResult("CameraResult"),
    CameraResultInconsistency("CameraResultInconsistency")
}
@AndroidEntryPoint
class CameraActivity : BaseActivity() {
    private lateinit var navController: NavController

    private val cameraViewModel by viewModels<CameraViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

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
                                intentSearch()
                            },
                            onInquiryCapture = {
                                intentInquiry(it)
                            }
                        )
                    }
                }
                composable(CameraPage.CameraCaptureResult.name) {
                    CameraCaptureResultScreen(
                        viewModel = cameraViewModel,
                        navController = navController,
                        onSearch = { response ->
                            cameraViewModel.setResult(0,response)
                            navController.navigate(CameraPage.CameraResult.name)
                        },
                        onDismissClick = {},
                        onGoInquiry = {}
                    )
                }
                composable(CameraPage.CameraResult.name) {
                    CameraResultScreen(
                        viewModel = cameraViewModel,
                        userViewModel = userViewModel,
                        onBackClick = {
                            intentSearch()
                        },
                        onPointClick = {
                            intentSearch()
                        },
                        onInconsistencyClick = { navController.navigate(CameraPage.CameraResultInconsistency.value) }
                    )
                }
                composable(CameraPage.CameraResultInconsistency.name) {
                    CameraResultInconsistencyScreen(
                        onBackClick = { navController.popBackStack()},
                        viewModel = cameraViewModel
                    )
                }
            }
        }
    }

    private fun intentSearch() {
        startActivity(
            Intent(
                this@CameraActivity,
                SearchActivity::class.java
            )
        )
    }

    private fun intentInquiry(byteArray: ByteArray) {
        val intent = Intent(
            this@CameraActivity,
            SearchActivity::class.java
        )
        intent.putExtra("isCamera",true)
        intent.putExtra("byteArray",byteArray)
        startActivity(intent)
        finish()
    }
}
