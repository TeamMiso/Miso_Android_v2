package com.miso.domain.model.auth.request

data class AuthLogInRequestModel(
    val email: String,
    val password: String,
    var deviceToken: String
)
