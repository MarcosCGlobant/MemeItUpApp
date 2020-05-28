package com.example.memeitupapp.data.service.api

import com.example.memeitupapp.data.service.response.MemeBaseResponse
import com.example.memeitupapp.data.service.response.MemeDetailResponse
import com.example.memeitupapp.data.service.response.MemeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("/")
    fun getMemes(): Call<MemeBaseResponse<List<MemeResponse>>>

    @GET("/memes/{memeId}")
    fun getMemeById(@Path("memeId") memeId: Int): Call<MemeBaseResponse<MemeDetailResponse>>
}