package com.globant.domain.service

import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.domain.util.Result

interface MemeService {
    fun getMemesFromApi(): Result<List<Meme>>
    fun getMemeByIdFromApi(memeId: Int): Result<MemeDetail>
}