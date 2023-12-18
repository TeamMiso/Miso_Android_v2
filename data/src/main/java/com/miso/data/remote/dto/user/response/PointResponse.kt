package com.miso.data.remote.dto.user.response

import com.miso.domain.model.user.response.PointResponseModel

data class PointResponse(
    val point: Int
)

fun PointResponse.toUserModel() =
    PointResponseModel(point = this.point)