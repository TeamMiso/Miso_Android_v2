package com.miso.data.remote.dto.shop.response

import com.miso.domain.model.shop.response.ShopListModel
import com.miso.domain.model.shop.response.ShopListResponseModel

data class ShopListResponse(
    val itemList: List<ShopList>
)

data class ShopList(
    val id: Long,
    val price: Int,
    val amount: Int,
    val name: String,
    val imageUrl: String
)

fun ShopListResponse.toShopModel() =
    ShopListResponseModel(
        itemList = this.itemList.map { it.toShopModel() }
    )

fun ShopList.toShopModel() =
    ShopListModel(
        id = this.id,
        price = this.price,
        amount = this.amount,
        name = this.name,
        imageUrl = this.imageUrl
    )