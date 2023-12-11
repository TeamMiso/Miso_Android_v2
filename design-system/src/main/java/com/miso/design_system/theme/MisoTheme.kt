package com.miso.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.miso.design_system.theme.color.LightColor
import com.miso.design_system.util.ApplySystemUi

@Composable
fun MisoTheme(
    colors: ColorTheme = if (!isSystemInDarkTheme()) LightColor else LightColor,
    typography: MisoTypography = MisoTypography,
    content: @Composable (colors: ColorTheme, typography: MisoTypography) -> Unit
) {
    content(colors, typography)
    ApplySystemUi(isSystemInDarkTheme())
}