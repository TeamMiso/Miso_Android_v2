package com.miso.presentation.ui.search

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miso.presentation.viewmodel.util.Event
import com.miso.design_system.component.bottombar.MisoBottomNavigationBar
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.base.BaseActivity
import com.miso.presentation.ui.camera.CameraActivity
import com.miso.presentation.ui.inquiry.screen.InquiryScreen
import com.miso.presentation.ui.result.screen.ResultScreen
import com.miso.presentation.ui.search.screen.SearchScreen
import com.miso.presentation.ui.search.screen.SearchableListScreen
import com.miso.presentation.ui.setting.screen.SettingScreen
import com.miso.presentation.ui.shop.screen.ShopDetailScreen
import com.miso.presentation.ui.shop.screen.ShopScreen
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
    Inquiry("Inquiry"),
    Setting("Setting")
}

enum class SubPage(val value: String) {
    SearchableList("SearchableList"),
    Result("Result"),
    ShopDetail("ShopDetail")
}

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    private val recyclablesViewModel by viewModels<RecyclablesViewModel>()
    private val shopViewModel by viewModels<ShopViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val purchaseViewModel by viewModels<PurchaseViewModel>()

    override fun init() {
        userViewModel.getUserInfo()
        lifecycleScope.launch {
            recyclablesViewModel.resultResponse.collect {
                if (it is Event.Success) {
                    recyclablesViewModel.saveResult(it.data!!)
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
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            MisoTheme { _, _ ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = MainPage.Search.name
                    ) {
                        composable(MainPage.Search.name) {
                            SearchScreen(
                                focusManager = LocalFocusManager.current,
                                viewModel = recyclablesViewModel,
                                lifecycleScope = lifecycleScope,
                                onSearchableListClick = { navController.navigate(SubPage.SearchableList.value) },
                                onResultClick = { navController.navigate(SubPage.Result.value) }
                            )
                        }
                        composable(MainPage.Shop.name) {
                            ShopScreen(
                                viewModel = shopViewModel,
                                onShopDetailClick = { navController.navigate(SubPage.ShopDetail.value) }
                            )
                        }
                        composable(MainPage.Camera.name) {
                            Text(text = "Camera")
                        }
                        composable(MainPage.Inquiry.name) {
                            InquiryScreen()
                        }
                        composable(MainPage.Setting.name) {
                            SettingScreen(
                                viewModel = userViewModel
                            )
                        }
                        composable(SubPage.SearchableList.name) {
                            SearchableListScreen(
                                viewModel = recyclablesViewModel,
                                onBackClick = { navController.popBackStack() },
                                onResultClick = { navController.navigate(SubPage.Result.value) }
                            )
                        }
                        composable(SubPage.Result.name) {
                            ResultScreen(
                                viewModel = recyclablesViewModel,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                        composable(SubPage.ShopDetail.name) {
                            ShopDetailScreen(
                                shopViewModel = shopViewModel,
                                userViewModel = userViewModel,
                                purchaseViewModel = purchaseViewModel,
                                onBackClick = { navController.popBackStack() },
                                onSearchClick = {
                                    navController.navigate(MainPage.Search.value){
                                        popUpTo(MainPage.Search.value){
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
                        onSearchClick = { navController.navigate(MainPage.Search.value) },
                        onShopClick = { navController.navigate(MainPage.Shop.value) },
                        onCameraClick = {
                            val intent = Intent(
                                this@SearchActivity,
                                CameraActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        },
                        onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                        onSettingClick = { navController.navigate(MainPage.Setting.value) }
                    )
                }
            }
        }
    }
}
