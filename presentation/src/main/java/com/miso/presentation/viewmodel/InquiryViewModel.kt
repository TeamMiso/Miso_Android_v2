package com.miso.presentation.viewmodel

import androidx.camera.core.CameraState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.miso.presentation.ui.inquiry.state.ByteArrayState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InquiryViewModel @Inject constructor(

) : ViewModel() {
    var isCamera = mutableStateOf(false)

    var byteArray = mutableStateOf(ByteArrayState())
}