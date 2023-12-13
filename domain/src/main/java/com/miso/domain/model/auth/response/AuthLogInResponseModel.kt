package com.miso.domain.model.auth.response

data class AuthLogInResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)
