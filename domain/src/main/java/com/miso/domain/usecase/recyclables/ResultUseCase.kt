package com.miso.domain.usecase.recyclables

import com.miso.domain.repository.RecyclablesRepository
import javax.inject.Inject

class ResultUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(recyclablesType: String) = kotlin.runCatching {
        recyclablesRepository.result(recyclablesType = recyclablesType)
    }
}