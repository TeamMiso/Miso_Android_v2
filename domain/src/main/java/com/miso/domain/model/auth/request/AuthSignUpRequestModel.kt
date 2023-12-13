package com.miso.domain.model.auth.request

data class AuthSignUpRequestModel(
    val email: String,
    val password: String,
    val passwordCheck: String
)