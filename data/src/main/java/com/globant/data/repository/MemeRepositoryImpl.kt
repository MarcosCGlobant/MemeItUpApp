package com.globant.data.repository

import com.globant.data.service.MemeServiceImpl
import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.domain.repository.MemeRepository
import com.globant.domain.util.NOT_FOUND
import com.globant.domain.util.Result

class MemeRepositoryImpl(private val memeService: MemeServiceImpl) : MemeRepository {

    override fun getMemeById(memeId: Int): Result<MemeDetail> {
        memeService.getMemeByIdFromApi(memeId).let {
            return when (it) {
                is Result.Success -> it
                is Result.Failure -> Result.Failure(Exception(NOT_FOUND))
            }
        }
    }

    override fun getMemes(): Result<List<Meme>> {
        memeService.getMemesFromApi().let {
            return when (it) {
                is Result.Success -> it
                is Result.Failure -> Result.Failure(Exception(NOT_FOUND))
            }
        }
    }
}