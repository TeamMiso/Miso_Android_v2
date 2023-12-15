package com.miso.domain.model.recyclables.response

data class SearchResponseModel(
    val title: String,
    val imageUrl: String,
    val recycleMethod: String,
    val recyclablesType: String
)