package com.globant.domain.usecase.implementation

import com.globant.domain.entity.MemeDetail
import com.globant.domain.service.MemeService
import com.globant.domain.usecase.GetMemeByIdUseCase
import com.globant.domain.util.Result

class GetMemeByIdUseCaseImpl(private val memeService: MemeService) : GetMemeByIdUseCase {
    override fun invoke(memeId: Int): Result<MemeDetail> =
            memeService.getMemeByIdFromApi(memeId)
}