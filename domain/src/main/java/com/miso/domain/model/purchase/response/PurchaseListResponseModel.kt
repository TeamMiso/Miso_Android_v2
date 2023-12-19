package com.miso.domain.model.purchase.response

data class PurchaseListResponseModel(
    val purchaseList: List<PurchaseListModel>
)

data class PurchaseListModel(
    val id: Long,
    val price: Int,
    val name: String,
    val imageUrl: String,
    val createdDate: String
)