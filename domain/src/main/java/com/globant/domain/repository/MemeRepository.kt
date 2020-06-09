package com.globant.domain.repository

import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.domain.util.Result

interface MemeRepository {
    fun getMemes(): Result<List<Meme>>
    fun getMemeById(memeId: Int): Result<MemeDetail>
}