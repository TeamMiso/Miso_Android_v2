package com.miso.domain.model.recyclables.response

data class ResultResponseModel(
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
