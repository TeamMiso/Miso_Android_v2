package com.miso.presentation.ui.camera

import android.util.Log
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.camera.screen.CameraScreen

enum class CameraPage(val value: String) {
    Camera("Camera")
}

class CameraActivity : BaseActivity() {
    private lateinit var navController: NavController
    override fun init() {
        setContent {
            navController = rememberNavController()
            navController.addOnDestinationChangedListener { controller, destination, arguments ->

            }
            NavHost(
                navController = navController as NavHostController,
                startDestination = CameraPage.Camera.value
            ) {
                composable(CameraPage.Camera.name) {
                    CameraScreen(
                        context = this@CameraActivity
                    )
                }
            }
        }
    }
}