package com.miso.data.remote.dto.recyclables.response

import com.miso.domain.model.recyclables.response.SearchResponseModel


data class SearchResponse(
    val title: String,
    val imageUrl: String,
    val recycleMethod: String,
    val recyclablesType: String
)

fun SearchResponse.toSearchModel() =
    SearchResponseModel(
        title = this.title,
        imageUrl = this.imageUrl,
        recycleMethod = this.recycleMethod,
        recyclablesType = this.recyclablesType
    )