package com.miso.presentation.ui.login

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.miso.presentation.viewmodel.util.Event
import com.miso.presentation.ui.login.screen.LoginScreen
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.search.SearchActivity
import com.miso.presentation.ui.sign_up.screen.SignUpScreen
import com.miso.presentation.ui.sign_up.screen.VerificationScreen
import com.miso.presentation.viewmodel.AuthViewModel
import com.miso.presentation.viewmodel.EmailViewModel
import com.miso.presentation.viewmodel.NotificationViewModel
import com.miso.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class LoginPage(val value: String) {
    Login("Login"),
    SignUp("SignUp"),
    Verification("Verification")
}

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val emailViewModel by viewModels<EmailViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val notificationViewModel by viewModels<NotificationViewModel>()

    override fun init() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                userViewModel.getUserInfoResponse.value is Event.Loading
            }
        }
        initNotification()
        userViewModel.getUserInfo()
        lifecycleScope.launch {
            userViewModel.getUserInfoResponse.collect {
                if (it is Event.Success) {
                    userViewModel.saveUserInfo(it.data!!)
                    pageSearch()
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            authViewModel.saveTokenResponse.collect {
                if (it is Event.Success) {
                    pageSearch()
                    finish()
                }
            }
        }
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = LoginPage.Login.value
            ) {
                composable(LoginPage.Login.name) {
                    LoginScreen(
                        focusManager = LocalFocusManager.current,
                        lifecycleScope = lifecycleScope,
                        viewModel = authViewModel,
                        onSignUpClick = { navController.navigate(LoginPage.SignUp.value) },
                        onLoginClick = { body ->
                            authViewModel.authLogIn(body = body)
                        }
                    )
                }
                composable(LoginPage.SignUp.name) {
                    SignUpScreen(
                        focusManager = LocalFocusManager.current,
                        onBackClick = { navController.popBackStack() },
                        viewModel = authViewModel,
                        onVerificationClick = { navController.navigate(LoginPage.Verification.value) },
                        onSignUpClick = { body ->
                            authViewModel.authSignUp(body = body)
                        }
                    )
                }
                composable(LoginPage.Verification.name) {
                    VerificationScreen(
                        viewModel = emailViewModel,
                        focusManager = LocalFocusManager.current,
                        onBackClick = { navController.popBackStack() },
                        onLoginClick = {
                            navController.navigate(LoginPage.Login.value) {
                                popUpTo(LoginPage.Login.value) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    private fun pageSearch() {
        startActivity(
            Intent(
                this,
                SearchActivity::class.java
            )
        )
    }

    private fun initNotification() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val deviceTokenSF = getSharedPreferences("deviceToken", MODE_PRIVATE)
                val deviceToken = task.result
                if (deviceTokenSF.getString("device", "") == deviceToken) {
                    notificationViewModel.saveDeviceToken(deviceToken = deviceToken)
                    setNotificationLogic(deviceToken = deviceToken)
                }
            }
        }
    }

    private fun setNotificationLogic(deviceToken: String) {
        lifecycleScope.launch {
            notificationViewModel.saveDeviceTokenResponse.collect {
                if (it is Event.Success) {
                    val deviceTokenSF = getSharedPreferences("deviceToken", MODE_PRIVATE)
                    deviceTokenSF.edit().putString("device", deviceToken).apply()
                }
            }
        }
    }
}