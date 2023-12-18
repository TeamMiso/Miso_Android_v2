package com.miso.domain.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryRepository {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>
}