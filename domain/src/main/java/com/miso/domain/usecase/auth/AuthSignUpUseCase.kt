package com.miso.domain.usecase.auth

import com.miso.domain.model.auth.request.AuthSignUpRequestModel
import com.miso.domain.repository.AuthRepository
import javax.inject.Inject

class AuthSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: AuthSignUpRequestModel) = kotlin.runCatching {
        authRepository.authSignUp(body = body)
    }
}