package com.miso.data.remote.dto.shop.response

import com.miso.domain.model.shop.response.ShopListDetailResponseModel

data class ShopListDetailResponse(
    val id: Long,
    val price: Int,
    val amount: Int,
    val name: String,
    val content: String,
    val imageUrl: String
)

fun ShopListDetailResponse.toShopModel() =
    ShopListDetailResponseModel(
        id = this.id,
        price = this.price,
        amount = this.amount,
        name = this.name,
        content = this.content,
        imageUrl = this.imageUrl
    )