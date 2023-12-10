package com.miso.design_system.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.miso.design_system.theme.color.LightColor
import com.miso.design_system.util.ApplySystemUi

@Composable
fun MisoTheme(
    colors: ColorTheme = LightColor,
    typography: MisoTypography = MisoTypography,
    content: @Composable (colors: ColorTheme, typography: MisoTypography) -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    content(colors, typography)
    ApplySystemUi(darkTheme)
}