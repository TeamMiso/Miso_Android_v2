package com.miso.data.remote.dto.auth.request

data class AuthLogInRequest(
    val email: String,
    val password: String,
)
