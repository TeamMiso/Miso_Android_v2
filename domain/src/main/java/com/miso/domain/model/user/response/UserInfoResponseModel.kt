package com.miso.domain.model.user.response

import java.util.UUID

data class UserInfoResponseModel(
    val id: UUID,
    val email: String,
    val password: String,
    var point: Int,
    val role: String
)
