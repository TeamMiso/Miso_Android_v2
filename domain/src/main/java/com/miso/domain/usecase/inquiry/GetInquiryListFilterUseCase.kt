package com.miso.domain.usecase.inquiry

import com.miso.domain.repository.InquiryRepository
import javax.inject.Inject

class GetInquiryListFilterUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(state: String) = kotlin.runCatching {
        inquiryRepository.getInquiryListFilter(state = state)
    }
}