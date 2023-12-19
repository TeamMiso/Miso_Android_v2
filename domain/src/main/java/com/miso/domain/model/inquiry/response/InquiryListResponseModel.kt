package com.miso.domain.model.inquiry.response

data class InquiryListResponseModel(
    val inquiryList: List<InquiryListModel>
)

data class InquiryListModel(
    val id: Long,
    val inquiryDate: String,
    val title: String,
    val inquiryStatus: String,
    val imageUrl: String?
)
