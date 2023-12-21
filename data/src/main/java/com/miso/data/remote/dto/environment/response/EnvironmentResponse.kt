package com.miso.data.remote.dto.environment.response

import com.miso.domain.model.environment.response.EnvironmentResponseModel

data class EnvironmentResponse(
    val title: String,
    val content: String,
    val imageUrl: String
)

fun EnvironmentResponse.toEnvironmentModel() =
    EnvironmentResponseModel(
        title = this.title,
        content = this.content,
        imageUrl = this.imageUrl
    )
