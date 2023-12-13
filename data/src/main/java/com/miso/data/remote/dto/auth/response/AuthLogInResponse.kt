package com.miso.data.remote.dto.auth.response

import com.miso.domain.model.auth.response.AuthLogInResponseModel

data class AuthLogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)

fun AuthLogInResponse.toLogInModel() =
    AuthLogInResponseModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        accessExp = this.accessExp,
        refreshExp = this.refreshExp
    )
