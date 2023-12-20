package com.miso.data.repository

import com.miso.data.remote.datasource.inquiry.InquiryDataSource
import com.miso.data.remote.dto.inquiry.response.toInquiryModel
import com.miso.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.miso.domain.model.inquiry.response.InquiryListResponseModel
import com.miso.domain.repository.InquiryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class InquiryRepositoryImpl @Inject constructor(
    private val remoteInquiryDataSource: InquiryDataSource
): InquiryRepository {
    override suspend fun requestInquiry(
        filePart: MultipartBody.Part?,
        inquiryPart: RequestBody
    ): Flow<Unit> {
        return remoteInquiryDataSource.requestInquiry(
            filePart = filePart,
            inquiryPart = inquiryPart
        )
    }

    override suspend fun getInquiryList(): Flow<InquiryListResponseModel> {
        return remoteInquiryDataSource.getInquiryList().map { it.toInquiryModel() }
    }

    override suspend fun getInquiryListAll(): Flow<InquiryListResponseModel> {
        return remoteInquiryDataSource.getInquiryListAll().map { it.toInquiryModel() }
    }

    override suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponseModel> {
        return remoteInquiryDataSource.getInquiryListDetail(id = id).map { it.toInquiryModel() }
    }
}