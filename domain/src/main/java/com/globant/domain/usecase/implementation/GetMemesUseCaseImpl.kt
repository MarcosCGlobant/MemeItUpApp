package com.globant.domain.usecase.implementation

import com.globant.domain.repository.MemeRepository
import com.globant.domain.usecase.GetMemesUseCase

class GetMemesUseCaseImpl(private val memeRepository: MemeRepository): GetMemesUseCase {
    override fun invoke()= memeRepository.getMemes()
}