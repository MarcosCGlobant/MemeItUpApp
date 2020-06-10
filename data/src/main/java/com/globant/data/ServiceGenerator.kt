package com.globant.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    private val httpClient = OkHttpClient.Builder().build()

    private val builder = Retrofit.Builder()
        .baseUrl(MEME_MAKER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        private const val MEME_MAKER_BASE_URL = "http://alpha-meme-maker.herokuapp.com"
    }
}