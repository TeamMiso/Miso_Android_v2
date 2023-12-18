package com.miso.domain.usecase.user

import com.miso.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        userRepository.getUserInfo()
    }
}