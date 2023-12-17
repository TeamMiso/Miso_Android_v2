package com.miso.data.remote.dto.recyclables.response

data class AiListResponse(
    val recyclablesList: List<AiList>
)

data class AiList(
    val id: Long,
    val title: String,
    val subTitle: String,
    val recycleMethod: String,
    val recyleTip: String,
    val recylcleCaution: String,
    val imageUrl: String,
    val recyclablesType: String,
    val recycleMark: String
)


