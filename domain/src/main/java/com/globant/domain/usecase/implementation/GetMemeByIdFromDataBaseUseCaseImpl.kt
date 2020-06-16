package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.usecase.GetMemeByIdFromDataBaseUseCase

class GetMemeByIdFromDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) : GetMemeByIdFromDataBaseUseCase {
    override fun invoke(memeId: Int): MemeDetailRoom = memeDataBase.getMemeByIdFromDataBase(memeId)
}