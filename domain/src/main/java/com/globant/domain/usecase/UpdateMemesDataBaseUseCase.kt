package com.globant.domain.usecase

import com.globant.domain.entity.MemeRoom

interface UpdateMemesDataBaseUseCase {
    fun invoke(listOfMeme: List<MemeRoom>)
}