package com.miso.data.remote.dto.recyclables.response

import com.miso.domain.model.recyclables.response.ResultResponseModel


data class ResultResponse(
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

fun ResultResponse.toResultModel() =
    ResultResponseModel(
        id = this.id,
        title = this.title,
        subTitle = this.subTitle,
        recycleMethod = this.recycleMethod,
        recycleTip = this.recycleTip,
        recycleCaution = this.recycleCaution,
        imageUrl = this.imageUrl,
        recyclablesType = this.recyclablesType,
        recycleMark = this.recycleMark
    )
