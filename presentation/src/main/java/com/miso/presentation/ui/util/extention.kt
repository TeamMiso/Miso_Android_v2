package com.miso.presentation.ui.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.toDateString(): String {
    return kotlin.runCatching {
        val originalDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS").parse(this)
        if (originalDate != null) {
            val outputFormatter = SimpleDateFormat("yy.MM.dd")
            outputFormatter.format(originalDate)
        } else {
            throw Exception()
        }
    }.onSuccess {
        it
    }.getOrElse {
        throw Exception()
    }
}