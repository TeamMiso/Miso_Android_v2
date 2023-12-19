package com.miso.data.remote.datasource.inquiry

import com.miso.data.remote.api.InquiryAPI
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

    override suspend fun getInquiryListAll(): Flow<InquiryListResponse> = flow {
        emit(
            MisoApiHandler<InquiryListResponse>()
                .httpRequest { api.getInquiryListAll() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}