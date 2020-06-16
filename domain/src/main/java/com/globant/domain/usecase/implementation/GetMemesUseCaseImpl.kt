package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Meme
import com.globant.domain.service.MemeService
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.util.Result

class GetMemesUseCaseImpl(private val memeService: MemeService) : GetMemesUseCase {
    override fun invoke(): Result<List<Meme>> = memeService.getMemesFromApi()
}