package com.miso.data.remote.dto.user.response

import com.miso.domain.model.user.response.UserInfoResponseModel
import java.util.UUID

data class UserInfoResponse(
    val id: UUID,
    val email: String,
    val password: String,
    val point: Int,
    val role: String
)

fun UserInfoResponse.toUserModel() =
    UserInfoResponseModel(
        id = this.id,
        email = this.email,
        password = this.password,
        point = this.point,
        role = this.role
    )
