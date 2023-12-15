package com.miso.data.remote.api

import com.miso.data.remote.dto.recyclables.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecyclablesAPI {
    @GET("recyclables/search")
    suspend fun search(
        @Query("searchValue") search: String
    ): SearchResponse
}