package com.miso.presentation.ui.login

import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.viewmodel.util.Event
import com.miso.presentation.ui.login.screen.LoginScreen
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.sign_up.screen.SignUpScreen
import com.miso.presentation.ui.sign_up.screen.VerificationScreen
import com.miso.presentation.viewmodel.AuthViewModel
import com.miso.presentation.viewmodel.EmailViewModel
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

    override fun init() {
        installSplashScreen()
        lifecycleScope.launch {
            authViewModel.saveTokenResponse.collect {
                if (it is Event.Success) {}
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
                        onVerificationClick = { navController.navigate(LoginPage.Verification.value) },
                        onSignUpClick = { body ->
                            authViewModel.authSignUp(body = body)
                        }
                    )
                }
                composable(LoginPage.Verification.name) {
                    VerificationScreen(
                        focusManager = LocalFocusManager.current,
                        onBackClick = { navController.popBackStack() },
                        onVerificationClick = { body ->
                            emailViewModel.email(body = body)
                        }
                    )
                }
            }
        }
    }
}