package com.miso.presentation.ui.search

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miso.design_system.component.bottombar.MisoBottomNavigationBar
import com.miso.design_system.theme.MisoTheme
import com.miso.presentation.ui.base.BaseActivity

enum class MainPage(val value: String) {
    Search("Search"),
    Shop("Shop"),
    Camera("Camera"),
    Inquiry("Inquiry"),
    Setting("Setting")
}

class SearchActivity : BaseActivity() {
    override fun init() {
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            MisoTheme { colors, typography ->
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
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colors.primary)
                            ) {
                                Text(text = "Search")
                            }
                        }
                        composable(MainPage.Shop.name) {
                            Text(text = "Shop")
                        }
                        composable(MainPage.Camera.name) {
                            Text(text = "Camera")
                        }
                        composable(MainPage.Inquiry.name) {
                            Text(text = "Inquiry")
                        }
                        composable(MainPage.Setting.name) {
                            Text(text = "Setting")
                        }
                    }
                    MisoBottomNavigationBar(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        currentRoute = currentRoute ?: "Search",
                        onSearchClick = { navController.navigate(MainPage.Search.value) },
                        onShopClick = { navController.navigate(MainPage.Shop.value) },
                        onCameraClick = { navController.navigate(MainPage.Camera.value) },
                        onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                        onSettingClick = { navController.navigate(MainPage.Setting.value) }
                    )
                }
            }
        }
    }
}
