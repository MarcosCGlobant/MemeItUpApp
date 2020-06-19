package com.globant.domain.usecase

import com.globant.domain.entity.MemeDetail
import com.globant.domain.util.Result

interface GetMemeByIdFromDataBaseUseCase {
    fun invoke(memeId: Int): Result<MemeDetail>
}
