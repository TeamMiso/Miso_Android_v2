package com.miso.domain.usecase.auth

import com.miso.domain.model.auth.response.AuthLogInResponseModel
import com.miso.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(token: AuthLogInResponseModel) = kotlin.runCatching {
        authRepository.saveToken(token = token)
    }
}