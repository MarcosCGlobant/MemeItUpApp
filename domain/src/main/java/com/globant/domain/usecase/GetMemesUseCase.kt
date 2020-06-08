package com.globant.domain.usecase

import com.globant.domain.entity.Meme
import com.globant.domain.util.Result

interface GetMemesUseCase {
    operator fun invoke(): Result<List<Meme>>
}