package com.miso.data.remote.dto.inquiry.response

import com.miso.domain.model.inquiry.response.InquiryListDetailResponseModel

data class InquiryListDetailResponse(
    val id: Long,
    val inquiryDate: String,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val inquiryStatus: String
)

fun InquiryListDetailResponse.toInquiryModel() =
    InquiryListDetailResponseModel(
        id = this.id,
        inquiryDate = this.inquiryDate,
        title = this.title,
        content = this.content,
        imageUrl = this.imageUrl,
        inquiryStatus = this.inquiryStatus
    )
