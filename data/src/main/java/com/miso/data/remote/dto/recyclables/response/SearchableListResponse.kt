package com.miso.data.remote.dto.recyclables.response

import com.miso.domain.model.recyclables.response.SearchableListModel
import com.miso.domain.model.recyclables.response.SearchableListResponseModel

data class SearchableListResponse(
    val recyclablesList: List<SearchableList>
)

data class SearchableList(
    val title: String,
    val imageUrl: String,
    val recycleMethod: String,
    val recyclablesType: String
)

fun SearchableListResponse.toSearchModel() =
    SearchableListResponseModel(
        recyclablesList = this.recyclablesList.map { it.toSearchModel() }
    )

fun SearchableList.toSearchModel() =
    SearchableListModel(
        title = this.title,
        imageUrl = this.imageUrl,
        recycleMethod = this.recycleMethod,
        recyclablesType = this.recyclablesType
    )
