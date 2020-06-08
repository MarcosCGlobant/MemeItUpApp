package com.globant.domain.usecase.implementation

import com.globant.domain.repository.MemeRepository
import com.globant.domain.usecase.GetMemeByIdUseCase

class GetMemeByIdUseCaseImpl(private val memeRepository: MemeRepository): GetMemeByIdUseCase {
    override fun invoke(memeId: Int) = memeRepository.getMemeById(memeId)
}