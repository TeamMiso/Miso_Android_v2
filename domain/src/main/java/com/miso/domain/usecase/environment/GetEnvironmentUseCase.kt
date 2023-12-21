package com.miso.domain.usecase.environment

import com.miso.domain.repository.EnvironmentRepository
import javax.inject.Inject

class GetEnvironmentUseCase @Inject constructor(
    private val environmentRepository: EnvironmentRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        environmentRepository.getEnvironment()
    }
}