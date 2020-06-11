package com.globant.data.service.api

import com.globant.data.service.response.MemeBaseResponse
import com.globant.data.service.response.MemeDetailResponse
import com.globant.data.service.response.MemeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("/")
    fun getMemes(): Call<MemeBaseResponse<List<MemeResponse>>>

    @GET("/memes/{memeId}")
    fun getMemeById(@Path("memeId") memeId: Int): Call<MemeBaseResponse<MemeDetailResponse>>
}