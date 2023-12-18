package com.miso.domain.model.recyclables.response

data class AiListResponseModel(
    val recyclablesList: List<AiListModel>
)

data class AiListModel(
    val id: Long,
    val title: String,
    val subTitle: String,
    val recycleMethod: String,
    val recycleTip: String,
    val recycleCaution: String,
    val imageUrl: String,
    val recyclablesType: String,
    val recycleMark: String
)