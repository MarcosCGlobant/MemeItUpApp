package com.globant.domain.usecase

import com.globant.domain.entity.Meme

interface UpdateMemesDataBaseUseCase {
    fun invoke(listOfMeme: List<Meme>)
}
