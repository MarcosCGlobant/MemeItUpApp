package com.globant.data.service

import com.globant.data.ServiceGenerator
import com.globant.data.mapper.MemeDetailMapperService
import com.globant.data.mapper.MemesMapperService
import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.data.service.api.ServiceApi
import com.globant.domain.service.MemeService
import com.globant.domain.util.NOT_FOUND
import com.globant.domain.util.Result

class MemeServiceImpl : MemeService {

    private val api = ServiceGenerator()
    private val mapperDetails = MemeDetailMapperService()
    private val mapper = MemesMapperService()

    override fun getMemesFromApi(): Result<List<Meme>> {
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

    override fun getMemeByIdFromApi(memeId: Int): Result<MemeDetail> {
        try {
            val callResponse = api.createService(ServiceApi::class.java).getMemeById(memeId)
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.data?.let { mapperDetails.transformMeme(it) }?.let { return Result.Success(it) }
        } catch (e: Exception) {
            return Result.Failure(Exception(NOT_FOUND))
        }
        return Result.Failure(Exception(NOT_FOUND))
    }
}