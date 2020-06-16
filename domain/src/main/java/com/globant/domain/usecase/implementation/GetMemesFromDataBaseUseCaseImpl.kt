package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.MemeRoom
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase

class GetMemesFromDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) :
    GetMemesFromDataBaseUseCase {
    override fun invoke(): List<MemeRoom> =
        memeDataBase.getAllMemesFromDataBase()
}