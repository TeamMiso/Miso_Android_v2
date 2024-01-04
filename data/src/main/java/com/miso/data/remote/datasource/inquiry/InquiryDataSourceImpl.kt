package com.miso.data.remote.datasource.inquiry

import com.miso.data.remote.api.InquiryAPI
import com.miso.data.remote.dto.inquiry.request.AnswerRequest
import com.miso.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.miso.data.remote.dto.inquiry.response.InquiryListResponse
import com.miso.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class InquiryDataSourceImpl @Inject constructor(
    private val api: InquiryAPI
): InquiryDataSource {
    override suspend fun requestInquiry(
        filePart: MultipartBody.Part?,
        inquiryPart: RequestBody
    ): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.requestInquiry(
                    filePart = filePart,
                    inquiryPart = inquiryPart
                ) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getInquiryList(): Flow<InquiryListResponse> = flow {
        emit(
            MisoApiHandler<InquiryListResponse>()
                .httpRequest { api.getInquiryList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponse> = flow {
        emit(
            MisoApiHandler<InquiryListDetailResponse>()
                .httpRequest { api.getInquiryListDetail(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun sendAnswer(id: Long, body: AnswerRequest): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.sendAnswer(id = id, body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}