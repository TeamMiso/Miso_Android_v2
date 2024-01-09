package com.miso.domain.repository

import com.miso.domain.model.inquiry.request.AnswerRequestModel
import com.miso.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.miso.domain.model.inquiry.response.InquiryListResponseModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryRepository {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>

    suspend fun getInquiryList(): Flow<InquiryListResponseModel>

    suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponseModel>

    suspend fun sendAnswer(id: Long, body: AnswerRequestModel): Flow<Unit>

    suspend fun getInquiryListFilter(state: String): Flow<InquiryListResponseModel>
}