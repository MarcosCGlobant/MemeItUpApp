package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.Meme
import com.globant.domain.usecase.UpdateMemesDataBaseUseCase

class UpdateMemesDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) : UpdateMemesDataBaseUseCase {
    override fun invoke(listOfMemes: List<Meme>) { memeDataBase.updateMemesInDataBase(listOfMemes) }
}
