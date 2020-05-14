package com.example.memeitupapp.data.service.api

import com.example.memeitupapp.data.service.response.MemeBaseResponse
import com.example.memeitupapp.data.service.response.MemeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {
    @GET("/")
    fun getMemes(): Call<MemeBaseResponse<List<MemeResponse>>>
}