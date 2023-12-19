package com.miso.domain.usecase.auth

import com.miso.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.logout()
    }
}