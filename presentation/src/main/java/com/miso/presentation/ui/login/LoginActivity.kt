package com.miso.presentation.ui.login

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.miso.presentation.ui.login.screen.LoginScreen
import com.miso.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    override fun init() {
        installSplashScreen()
        setContent {
            LoginScreen(
                focusManager = LocalFocusManager.current
            )
        }
    }
}