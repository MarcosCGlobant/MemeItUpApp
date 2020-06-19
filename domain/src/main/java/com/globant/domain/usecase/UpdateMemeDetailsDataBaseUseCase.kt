package com.globant.domain.usecase

import com.globant.domain.entity.MemeDetail

interface UpdateMemeDetailsDataBaseUseCase {
    fun invoke(memeDetail: MemeDetail)
}
