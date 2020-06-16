package com.globant.domain.usecase

import com.globant.domain.entity.MemeDetailRoom

interface UpdateMemeDetailsDataBaseUseCase {
    fun invoke(memeDetail: MemeDetailRoom)
}