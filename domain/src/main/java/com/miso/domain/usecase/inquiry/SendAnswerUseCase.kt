package com.miso.domain.usecase.inquiry

import com.miso.domain.model.inquiry.request.AnswerRequestModel
import com.miso.domain.repository.InquiryRepository
import javax.inject.Inject

class SendAnswerUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(id: Long, body: AnswerRequestModel) = kotlin.runCatching {
        inquiryRepository.sendAnswer(id = id, body = body)
    }
}