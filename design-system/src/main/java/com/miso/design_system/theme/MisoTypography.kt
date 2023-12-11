package com.miso.design_system.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miso.design_system.R

object MisoTypography {
    private val suit = FontFamily(
        Font(R.font.suitv1_bold, FontWeight.Bold),
        Font(R.font.suitv1_extrabold, FontWeight.ExtraBold),
        Font(R.font.suitv1_extralight, FontWeight.ExtraLight),
        Font(R.font.suitv1_light, FontWeight.Light),
        Font(R.font.suitv1_medium, FontWeight.Medium),
        Font(R.font.suitv1_regular, FontWeight.Normal),
        Font(R.font.suitv1_semibold, FontWeight.SemiBold),
        Font(R.font.suitv1_thin, FontWeight.Thin),
    )

    @Stable
    val titleLarge = TextStyle(
        fontFamily = suit,
        fontSize = 46.sp,
        lineHeight = 64.sp
    )

    @Stable
    val titleMedium = TextStyle(
        fontFamily = suit,
        fontSize = 37.sp,
        lineHeight = 56.sp
    )

    @Stable
    val titleSmall = TextStyle(
        fontFamily = suit,
        fontSize = 32.sp,
        lineHeight = 48.sp
    )

    @Stable
    val textLarge = TextStyle(
        fontFamily = suit,
        fontSize = 25.sp,
        lineHeight = 40.sp
    )

    @Stable
    val textMedium = TextStyle(
        fontFamily = suit,
        fontSize = 20.sp,
        lineHeight = 32.sp
    )

    @Stable
    val textSmall = TextStyle(
        fontFamily = suit,
        fontSize = 15.sp,
        lineHeight = 24.sp
    )

    @Stable
    val captionLarge = TextStyle(
        fontFamily = suit,
        fontSize = 12.sp,
        lineHeight = 15.6.sp
    )

    @Stable
    val captionSmall = TextStyle(
        fontFamily = suit,
        fontSize = 10.sp,
        lineHeight = 16.sp
    )

    @Stable
    val buttonLarge = TextStyle(
        fontFamily = suit,
        fontSize = 16.sp,
        lineHeight = 40.sp
    )

    @Stable
    val buttonMedium = TextStyle(
        fontFamily = suit,
        fontSize = 16.sp,
        lineHeight = 32.sp
    )

    @Stable
    val buttonSmall = TextStyle(
        fontFamily = suit,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}