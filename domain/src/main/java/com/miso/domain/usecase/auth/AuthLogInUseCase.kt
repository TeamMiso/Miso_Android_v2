package com.miso.domain.usecase.auth

import com.miso.domain.model.auth.request.AuthLogInRequestModel
import com.miso.domain.repository.AuthRepository
import javax.inject.Inject

class AuthLogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: AuthLogInRequestModel) = kotlin.runCatching {
        authRepository.authLogIn(body = body)
    }
}