package com.miso.presentation.ui.search

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miso.presentation.viewmodel.util.Event
import com.miso.design_system.component.bottombar.MisoBottomNavigationBar
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.camera.CameraActivity
import com.miso.presentation.ui.inquiry.component.bottomsheet.SelectFilterBottomSheet
import com.miso.presentation.ui.inquiry.screen.InquiryAnswerScreen
import com.miso.presentation.ui.inquiry.screen.InquiryListDetailScreen
import com.miso.presentation.ui.inquiry.screen.InquiryListScreen
import com.miso.presentation.ui.inquiry.screen.InquiryScreen
import com.miso.presentation.ui.login.LoginActivity
import com.miso.presentation.ui.result.screen.ResultScreen
import com.miso.presentation.ui.search.screen.SearchScreen
import com.miso.presentation.ui.search.screen.SearchableListScreen
import com.miso.presentation.ui.search.screen.TodayEnvironmentTipScreen
import com.miso.presentation.ui.setting.screen.SettingScreen
import com.miso.presentation.ui.shop.screen.PurchaseListScreen
import com.miso.presentation.ui.shop.screen.ShopDetailScreen
import com.miso.presentation.ui.shop.screen.ShopScreen
import com.miso.presentation.viewmodel.AuthViewModel
import com.miso.presentation.viewmodel.CameraViewModel
import com.miso.presentation.viewmodel.EnvironmentViewModel
import com.miso.presentation.viewmodel.InquiryViewModel
import com.miso.presentation.viewmodel.NotificationViewModel
import com.miso.presentation.viewmodel.PurchaseViewModel
import com.miso.presentation.viewmodel.RecyclablesViewModel
import com.miso.presentation.viewmodel.ShopViewModel
import com.miso.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class MainPage(val value: String) {
    Search("Search"),
    Shop("Shop"),
    Camera("Camera"),
    InquiryList("InquiryList"),
    Setting("Setting")
}

enum class SubPage(val value: String) {
    SearchableList("SearchableList"),
    Result("Result"),
    ShopDetail("ShopDetail"),
    Inquiry("Inquiry"),
    PurchaseList("PurchaseList"),
    InquiryListDetail("InquiryListDetail"),
    TodayEnvironmentTip("TodayEnvironmentTip"),
    InquiryAnswer("InquiryAnswer")
}

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val recyclablesViewModel by viewModels<RecyclablesViewModel>()
    private val shopViewModel by viewModels<ShopViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val inquiryViewModel by viewModels<InquiryViewModel>()
    private val purchaseViewModel by viewModels<PurchaseViewModel>()
    private val notificationViewModel by viewModels<NotificationViewModel>()
    private val environmentViewModel by viewModels<EnvironmentViewModel>()
    private val cameraViewModel by viewModels<CameraViewModel>()

    private lateinit var navController: NavController

    @OptIn(ExperimentalMaterialApi::class)
    override fun init() {
        userViewModel.getUserInfo()
        lifecycleScope.launch {
            recyclablesViewModel.resultResponse.collect {
                if (it is Event.Success) {
                    recyclablesViewModel.saveResult(it.data!!)
                    navController.navigate(SubPage.Result.value)
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getUserInfoResponse.collect {
                if (it is Event.Success) {
                    userViewModel.saveUserInfo(it.data!!)
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getPointResponse.collect {
                if (it is Event.Success) {
                    userViewModel.savePoint(it.data!!)
                }
            }
        }
        lifecycleScope.launch {
            authViewModel.logoutResponse.collect {
                if (it is Event.Success) {
                    pageLogIn()
                    finish()
                }
            }
        }

        inquiryViewModel.isCamera.value = intent.getBooleanExtra("isCamera", false)
        inquiryViewModel.byteArray.value =
            inquiryViewModel.byteArray.value.copy(intent.getByteArrayExtra("byteArray"))

        setContent {
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) { isGrantedMap: Map<String, Boolean> -> }

            LaunchedEffect("Permission") {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                )
            }

            navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val bottomSheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val scope = rememberCoroutineScope()
            var state by remember { mutableStateOf("") }

            ModalBottomSheetLayout(
                sheetContent = {
                    SelectFilterBottomSheet(
                        coroutineScope = scope,
                        bottomSheetState = bottomSheetState,
                        state = {
                            state = it
                        }
                    )
                },
                sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                sheetState = bottomSheetState
            ) {
                MisoTheme { _, _ ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                    ) {
                        NavHost(
                            navController = navController as NavHostController,
                            startDestination = MainPage.Search.name
                        ) {
                            composable(MainPage.Search.name) {
                                SearchScreen(
                                    focusManager = LocalFocusManager.current,
                                    viewModel = recyclablesViewModel,
                                    inquiryViewModel = inquiryViewModel,
                                    lifecycleScope = lifecycleScope,
                                    onSearchableListClick = { navController.navigate(SubPage.SearchableList.value) },
                                    onInquiryCamera = { navController.navigate(SubPage.Inquiry.name) },
                                    onTodayEnvironmentTipClick = { navController.navigate(SubPage.TodayEnvironmentTip.value) }
                                )
                            }
                            composable(MainPage.Shop.name) {
                                ShopScreen(
                                    viewModel = shopViewModel,
                                    onShopDetailClick = { navController.navigate(SubPage.ShopDetail.value) },
                                    onPurchaseListClick = { navController.navigate(SubPage.PurchaseList.value) }
                                )
                            }
                            composable(MainPage.Camera.name) {
                                pageCamera()
                                finish()
                            }
                            composable(MainPage.InquiryList.name) {
                                InquiryListScreen(
                                    state = state,
                                    lifecycleScope = lifecycleScope,
                                    inquiryViewModel = inquiryViewModel,
                                    notificationViewModel = notificationViewModel,
                                    onInquiryClick = { navController.navigate(SubPage.Inquiry.value) },
                                    onInquiryListDetailClick = { navController.navigate(SubPage.InquiryListDetail.value) },
                                    onBottomSheetClick = {
                                        scope.launch {
                                            bottomSheetState.show()
                                        }
                                    }
                                )
                            }
                            composable(MainPage.Setting.name) {
                                SettingScreen(
                                    context = this@SearchActivity,
                                    viewModel = userViewModel,
                                    onLogoutClick = { authViewModel.logout() }
                                )
                            }
                            composable(SubPage.Inquiry.name) {
                                InquiryScreen(
                                    context = this@SearchActivity,
                                    focusManager = LocalFocusManager.current,
                                    onCameraClick = {
                                        val intent = Intent(
                                            this@SearchActivity,
                                            CameraActivity::class.java
                                        )
                                        intent.putExtra("isInquiry", true)
                                        startActivity(intent)
                                        finish()
                                    },
                                    viewModel = inquiryViewModel,
                                    cameraViewModel = cameraViewModel,
                                    lifecycleScope = lifecycleScope,
                                    navController = navController,
                                    onInquiryClick = { filePart, inquiryPart ->
                                        inquiryViewModel.requestInquiry(
                                            filePart = filePart,
                                            inquiryPart = inquiryPart
                                        )
                                    },
                                    onGotoInquiry = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                            composable(SubPage.SearchableList.name) {
                                SearchableListScreen(
                                    viewModel = recyclablesViewModel,
                                    onBackClick = { navController.popBackStack() },
                                )
                            }
                            composable(SubPage.Result.name) {
                                ResultScreen(
                                    viewModel = recyclablesViewModel,
                                    onBackClick = { navController.popBackStack() },
                                    onSearchClick = {
                                        navController.navigate(MainPage.Search.value) {
                                            popUpTo(MainPage.Search.value) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }
                            composable(SubPage.ShopDetail.name) {
                                ShopDetailScreen(
                                    shopViewModel = shopViewModel,
                                    userViewModel = userViewModel,
                                    purchaseViewModel = purchaseViewModel,
                                    onBackClick = { navController.popBackStack() },
                                    onSearchClick = {
                                        navController.navigate(MainPage.Search.value) {
                                            popUpTo(MainPage.Search.value) {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    onCameraClick = {
                                        pageCamera()
                                        finish()
                                    },
                                    onPurchaseListClick = { navController.navigate(SubPage.PurchaseList.value) }
                                )
                            }
                            composable(SubPage.PurchaseList.name) {
                                PurchaseListScreen(
                                    viewModel = purchaseViewModel,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable(SubPage.InquiryListDetail.name) {
                                InquiryListDetailScreen(
                                    inquiryViewModel = inquiryViewModel,
                                    notificationViewModel = notificationViewModel,
                                    userViewModel = userViewModel,
                                    onBackClick = { navController.popBackStack() },
                                    onInquiryAnswerClick = { navController.navigate(SubPage.InquiryAnswer.value) }
                                )
                            }
                            composable(SubPage.TodayEnvironmentTip.name) {
                                TodayEnvironmentTipScreen(
                                    viewModel = environmentViewModel,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable(SubPage.InquiryAnswer.name) {
                                InquiryAnswerScreen(
                                    viewModel = inquiryViewModel,
                                    focusManager = LocalFocusManager.current,
                                    onBackClick = { navController.popBackStack() },
                                    onSearchClick = {
                                        navController.navigate(MainPage.Search.value) {
                                            popUpTo(MainPage.Search.value) {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    onInquiryListDetailClick = {
                                        navController.navigate(SubPage.InquiryListDetail.value) {
                                            popUpTo(SubPage.InquiryListDetail.value) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }
                        }
                        MisoBottomNavigationBar(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            isVisible = currentRoute in MainPage.values().map { it.name },
                            currentRoute = currentRoute ?: "Search",
                            onSearchClick = {
                                navController.navigate(MainPage.Search.value) {
                                    launchSingleTop = true
                                }
                            },
                            onShopClick = {
                                navController.navigate(MainPage.Shop.value) {
                                    launchSingleTop = true
                                }
                            },
                            onCameraClick = { pageCamera() },
                            onInquiryClick = {
                                navController.navigate(MainPage.InquiryList.value) {
                                    launchSingleTop = true
                                }
                            },
                            onSettingClick = {
                                navController.navigate(MainPage.Setting.value) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun pageLogIn() {
        startActivity(
            Intent(
                this,
                LoginActivity::class.java
            )
        )
    }

    private fun pageCamera() {
        startActivity(
            Intent(
                this,
                CameraActivity::class.java
            )
        )
    }
}
