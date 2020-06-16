package com.globant.domain.usecase

import com.globant.domain.entity.MemeDetailRoom

interface GetMemeByIdFromDataBaseUseCase {
    fun invoke(memeId: Int): MemeDetailRoom
}