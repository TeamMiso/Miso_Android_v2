package com.miso.presentation.ui.util

import java.text.NumberFormat
import java.util.Locale

fun formatNumber(number: Int): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
}