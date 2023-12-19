package com.miso.domain.usecase.inquiry

import com.miso.domain.repository.InquiryRepository
import javax.inject.Inject

class GetInquiryListAllUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        inquiryRepository.getInquiryListAll()
    }
}