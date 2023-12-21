package com.miso.data.remote.datasource.inquiry

import com.miso.data.remote.dto.inquiry.request.AnswerRequest
import com.miso.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.miso.data.remote.dto.inquiry.response.InquiryListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryDataSource {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>

    suspend fun getInquiryList(): Flow<InquiryListResponse>

    suspend fun getInquiryListAll(): Flow<InquiryListResponse>

    suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponse>

    suspend fun sendAnswer(id: Long, body: AnswerRequest): Flow<Unit>
}