package com.globant.di

import com.globant.data.service.MemeServiceImpl
import com.globant.domain.service.MemeService
import com.globant.domain.usecase.GetMemeByIdUseCase
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.usecase.implementation.GetMemeByIdUseCaseImpl
import com.globant.domain.usecase.implementation.GetMemesUseCaseImpl
import org.koin.dsl.module

val servicesModule = module {
    single <MemeService> { MemeServiceImpl() }
}

val useCasesModule = module {
    single <GetMemesUseCase> { GetMemesUseCaseImpl(get()) }
    single <GetMemeByIdUseCase> { GetMemeByIdUseCaseImpl(get()) }
}