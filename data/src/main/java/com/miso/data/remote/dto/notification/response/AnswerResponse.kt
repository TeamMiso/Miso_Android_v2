package com.miso.data.remote.dto.notification.response

import com.miso.domain.model.notification.response.AnswerResponseModel

data class AnswerResponse(
    val id: Long,
    val title: String,
    val content: String
)

fun AnswerResponse.toNotificationModel() =
    AnswerResponseModel(
        id = this.id,
        title = this.title,
        content = this.content
    )