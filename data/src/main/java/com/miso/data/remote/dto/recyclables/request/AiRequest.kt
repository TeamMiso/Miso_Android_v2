package com.miso.data.remote.dto.recyclables.request

import retrofit2.http.Multipart

data class AiRequest(
    val recyclables: Multipart
)