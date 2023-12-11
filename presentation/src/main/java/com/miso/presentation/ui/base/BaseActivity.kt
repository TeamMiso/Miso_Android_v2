package com.miso.presentation.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback

abstract class BaseActivity : ComponentActivity() {
    private var doubleBackToExitPressedOnce = false
    private var backPressedTimestamp = 0L
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            controlTheStackWhenBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        init()
    }

    abstract fun init()

    protected fun controlTheStackWhenBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (doubleBackToExitPressedOnce && currentTime - backPressedTimestamp <= 2000) {
            finishAffinity()
        } else {
            doubleBackToExitPressedOnce = true
            backPressedTimestamp = currentTime
            Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }
}