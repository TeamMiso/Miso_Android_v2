package com.miso.data.remote.datasource.inquiry

import com.miso.data.remote.dto.inquiry.response.InquiryListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryDataSource {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>

    suspend fun getInquiryList(): Flow<InquiryListResponse>

    suspend fun getInquiryListAll(): Flow<InquiryListResponse>
}