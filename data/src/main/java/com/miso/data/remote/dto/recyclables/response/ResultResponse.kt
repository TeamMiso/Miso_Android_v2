package com.miso.data.remote.dto.recyclables.response

import com.miso.domain.model.recyclables.response.ResultResponseModel


data class ResultResponse(
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

fun ResultResponse.toResultModel() =
    ResultResponseModel(
        id = this.id,
        title = this.title,
        subTitle = this.subTitle,
        recycleMethod = this.recycleMethod,
        recyleTip = this.recyleTip,
        recylcleCaution = this.recylcleCaution,
        imageUrl = this.imageUrl,
        recyclablesType = this.recyclablesType,
        recycleMark = this.recycleMark
    )
