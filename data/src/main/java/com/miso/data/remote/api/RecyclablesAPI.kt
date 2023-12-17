package com.miso.data.remote.api

import com.miso.data.remote.dto.recyclables.request.AiRequest
import com.miso.data.remote.dto.recyclables.response.AiListResponse
import com.miso.data.remote.dto.recyclables.response.ResultResponse
import com.miso.data.remote.dto.recyclables.response.SearchResponse
import com.miso.data.remote.dto.recyclables.response.SearchableListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecyclablesAPI {
    @GET("recyclables/search")
    suspend fun search(
        @Query("searchValue") search: String
    ): SearchResponse

    @GET("recyclables/all")
    suspend fun searchableList(): SearchableListResponse

    @GET("recyclables")
    suspend fun result(
        @Query("recyclablesType") recyclablesType: String
    ): ResultResponse

    @POST("recyclables/process")
    suspend fun getAiAnswerList(
        @Body body: AiRequest
    ): AiListResponse
}