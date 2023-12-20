package com.miso.data.remote.dto.notification.response

import com.miso.domain.model.notification.response.AnswerResponseModel

data class AnswerResponse(
    val id: Long,
    val answer: String
)

fun AnswerResponse.toNotificationModel() =
    AnswerResponseModel(
        id = this.id,
        answer = this.answer
    )