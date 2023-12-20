package com.miso.domain.model.inquiry.response

data class InquiryListDetailResponseModel(
    val id: Long,
    val inquiryDate: String,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val inquiryStatus: String
)
