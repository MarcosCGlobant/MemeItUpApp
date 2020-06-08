package com.globant.domain.usecase

import com.globant.domain.entity.MemeDetail
import com.globant.domain.util.Result

interface GetMemeByIdUseCase {
    operator fun invoke(memeId: Int): Result<MemeDetail>
}