package com.miso.data.remote.api

import com.miso.data.remote.dto.inquiry.response.InquiryListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface InquiryAPI {
    @Multipart
    @POST("inquiry")
    suspend fun requestInquiry(
        @Part filePart: MultipartBody.Part?,
        @Part("inquiry") inquiryPart: RequestBody
    )

    @GET("inquiry")
    suspend fun getInquiryList(): InquiryListResponse

    @GET("inquiry/all")
    suspend fun getInquiryListAll(): InquiryListResponse
}