package com.globant.domain.usecase.implementation

import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.usecase.UpdateMemeDetailsDataBaseUseCase

class UpdateMemeDetailsDataBaseUseCaseImpl(private val memeDataBase: MemeRoomDataBase) : UpdateMemeDetailsDataBaseUseCase {
    override fun invoke(memeDetail: MemeDetailRoom) {
        memeDataBase.updateMemeDetailInDataBase(memeDetail)
    }
}