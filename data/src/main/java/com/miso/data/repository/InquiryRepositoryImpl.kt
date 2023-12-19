package com.miso.data.repository

import com.miso.data.remote.datasource.inquiry.InquiryDataSource
import com.miso.domain.repository.InquiryRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class InquiryRepositoryImpl @Inject constructor(
    private val inquiryDataSource: InquiryDataSource
): InquiryRepository {
    override suspend fun requestInquiry(
        filePart: MultipartBody.Part?,
        inquiryPart: RequestBody
    ): Flow<Unit> {
        return inquiryDataSource.requestInquiry(
            filePart = filePart,
            inquiryPart = inquiryPart
        )
    }

}