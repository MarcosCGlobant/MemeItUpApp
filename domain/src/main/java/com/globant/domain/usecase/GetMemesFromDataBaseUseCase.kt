package com.globant.domain.usecase

import com.globant.domain.entity.MemeRoom

interface GetMemesFromDataBaseUseCase {
    fun invoke(): List<MemeRoom>
}