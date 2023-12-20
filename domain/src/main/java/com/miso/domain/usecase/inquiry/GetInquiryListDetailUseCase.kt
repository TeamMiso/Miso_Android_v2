package com.miso.domain.usecase.inquiry

import com.miso.domain.repository.InquiryRepository
import javax.inject.Inject

class GetInquiryListDetailUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        inquiryRepository.getInquiryListDetail(id = id)
    }
}