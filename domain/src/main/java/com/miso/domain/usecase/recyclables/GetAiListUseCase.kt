package com.miso.domain.usecase.recyclables

import com.miso.domain.repository.RecyclablesRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class GetAiListUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(recyclables: MultipartBody.Part) = kotlin.runCatching {
        recyclablesRepository.getAiAnswerList(recyclables = recyclables)
    }
}