package com.miso.domain.model.shop.response

data class ShopListDetailResponseModel(
    val id: Long,
    val price: Int,
    val amount: Int,
    val name: String,
    val content: String,
    val imageUrl: String
)
