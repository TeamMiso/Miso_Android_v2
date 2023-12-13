package com.miso.domain.usecase.email

import com.miso.domain.model.email.request.EmailRequestModel
import com.miso.domain.repository.EmailRepository
import javax.inject.Inject

class EmailUseCase @Inject constructor(
    private val emailRepository: EmailRepository
) {
    suspend operator fun invoke(body: EmailRequestModel) = kotlin.runCatching {
        emailRepository.email(body = body)
    }
}