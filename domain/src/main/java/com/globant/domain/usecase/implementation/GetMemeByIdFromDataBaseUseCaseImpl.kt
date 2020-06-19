package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.MemeDetail
import com.globant.domain.usecase.GetMemeByIdFromDataBaseUseCase
import com.globant.domain.util.Result

class GetMemeByIdFromDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) : GetMemeByIdFromDataBaseUseCase {
    override fun invoke(memeId: Int): Result<MemeDetail> = memeDataBase.getMemeByIdFromDataBase(memeId)
}
