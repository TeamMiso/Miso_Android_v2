package com.miso.data.remote.dto.purchase.response

import com.miso.domain.model.purchase.response.PurchaseListModel
import com.miso.domain.model.purchase.response.PurchaseListResponseModel

data class PurchaseListResponse(
    val purchaseList: List<PurchaseList>
)

data class PurchaseList(
    val id: Long,
    val price: Int,
    val name: String,
    val imageUrl: String,
    val createdDate: String
)

fun PurchaseListResponse.toPurchaseModel() =
    PurchaseListResponseModel(
        purchaseList = this.purchaseList.map { it.toPurchaseModel() }
    )

fun PurchaseList.toPurchaseModel() =
    PurchaseListModel(
        id = this.id,
        price = this.price,
        name = this.name,
        imageUrl = this.imageUrl,
        createdDate = this.createdDate
    )