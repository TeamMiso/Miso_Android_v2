package com.miso.data.remote.dto.inquiry.response

import com.miso.domain.model.inquiry.response.InquiryListModel
import com.miso.domain.model.inquiry.response.InquiryListResponseModel

data class InquiryListResponse(
    val inquiryList: List<InquiryList>
)

data class InquiryList(
    val id: Long,
    val inquiryDate: String,
    val title: String,
    val inquiryStatus: String,
    val imageUrl: String?
)

fun InquiryListResponse.toInquiryModel() =
    InquiryListResponseModel(
        inquiryList = this.inquiryList.map { it.toInquiryModel() }
    )

fun InquiryList.toInquiryModel() =
    InquiryListModel(
        id = this.id,
        inquiryDate = this.inquiryDate,
        title = this.title,
        inquiryStatus = this.inquiryStatus,
        imageUrl = this.imageUrl
    )
