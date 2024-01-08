package com.miso.design_system.component.button

sealed class ButtonState {
    object OutLine: ButtonState()
    object Normal: ButtonState()
}