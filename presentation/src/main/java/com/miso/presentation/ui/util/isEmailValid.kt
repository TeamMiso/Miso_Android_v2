package com.miso.presentation.ui.util

fun String.isEmailValid(): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return matches(emailRegex)
}