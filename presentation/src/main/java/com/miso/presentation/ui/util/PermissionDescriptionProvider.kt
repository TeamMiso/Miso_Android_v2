package com.miso.presentation.ui.util

import android.content.Context

interface PermissionDescriptionProvider {
    fun getTitle(context: Context): String

    fun getDescription(context: Context, isPermissionPermanentDenial: Boolean): String
}

class CameraPermissionDescriptionProvider : PermissionDescriptionProvider {
    override fun getTitle(context: Context): String {
        return "[필수] 카메라 권한 설정"
    }

    override fun getDescription(context: Context, isPermissionPermanentDenial: Boolean): String {
        val description = "MISO가 도움을 드릴수 있게 카메라 권한을 허용해 주세요!"
        return if(isPermissionPermanentDenial) {
            "${description}\n${"설정에서 권한을 설정해 주세요."}"
        } else {
            description
        }
    }
}

class ReadMediaImagesPermissionDescriptionProvider : PermissionDescriptionProvider {
    override fun getTitle(context: Context): String {
        return "[필수] 갤러리 접근 권한 설정"
    }

    override fun getDescription(context: Context, isPermissionPermanentDenial: Boolean): String {
        val description = "이미지 등록을 위해 권한을 허용해 주세요!"
        return if(isPermissionPermanentDenial) {
            "${description}\n${"설정에서 권한을 설정해 주세요."}"
        } else {
            description
        }
    }
}