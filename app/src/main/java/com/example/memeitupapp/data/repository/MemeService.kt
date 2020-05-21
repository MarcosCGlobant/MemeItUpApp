package com.example.memeitupapp.data.repository

import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.mapper.MemesMapperService
import com.example.memeitupapp.data.service.ServiceGenerator
import com.example.memeitupapp.data.service.api.ServiceApi
import com.example.memeitupapp.util.NOT_FOUND
import com.example.memeitupapp.util.Result

class MemeService {

    private val api: ServiceGenerator = ServiceGenerator()
    private val mapper: MemesMapperService = MemesMapperService()

    fun getMemesFromApi(): Result<List<Meme>> {
        try {
            val callResponse = api.createService(ServiceApi::class.java).getMemes()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.data?.let {
                    mapper.transform(it)
                }?.let {
                    return Result.Success(it)
                }
        } catch (e: Exception) {
            return Result.Failure(Exception(NOT_FOUND))
        }
        return Result.Failure(Exception(NOT_FOUND))
    }
}