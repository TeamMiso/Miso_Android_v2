package com.miso.domain.model.recyclables.response

data class SearchableListResponseModel(
    val recyclablesList: List<SearchableListModel>
)

data class SearchableListModel(
    val title: String,
    val imageUrl: String,
    val recycleMethod: String,
    val recyclablesType: String
)
