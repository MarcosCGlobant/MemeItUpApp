package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.Meme
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase
import com.globant.domain.util.Result

class GetMemesFromDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) : GetMemesFromDataBaseUseCase {
    override fun invoke(): Result<List<Meme>> = memeDataBase.getAllMemesFromDataBase()
}