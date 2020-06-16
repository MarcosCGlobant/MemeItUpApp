package com.globant.di

import androidx.room.Room
import com.globant.data.database.MemeRoomDataBaseImpl
import com.globant.data.service.MemeServiceImpl
import com.globant.domain.service.MemeService
import com.globant.domain.usecase.UpdateMemeDetailsDataBaseUseCase
import com.globant.domain.usecase.UpdateMemesDataBaseUseCase
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase
import com.globant.domain.usecase.GetMemeByIdFromDataBaseUseCase
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.usecase.GetMemeByIdUseCase
import com.globant.domain.usecase.implementation.UpdateMemesDataBaseUseCaseImpl
import com.globant.domain.usecase.implementation.UpdateMemeDetailsDataBaseUseCaseImpl
import com.globant.domain.usecase.implementation.GetMemesFromDataBaseUseCaseImpl
import com.globant.domain.usecase.implementation.GetMemeByIdFromDataBaseUseCaseImpl
import com.globant.domain.usecase.implementation.GetMemeByIdUseCaseImpl
import com.globant.domain.usecase.implementation.GetMemesUseCaseImpl
import org.koin.dsl.module

val servicesModule = module {
    single<MemeService> { MemeServiceImpl() }
}

val useCasesModule = module {
    single<GetMemesUseCase> { GetMemesUseCaseImpl(get()) }
    single<GetMemeByIdUseCase> { GetMemeByIdUseCaseImpl(get()) }
    single<GetMemeByIdFromDataBaseUseCase> { GetMemeByIdFromDataBaseUseCaseImpl(get()) }
    single<GetMemesFromDataBaseUseCase> { GetMemesFromDataBaseUseCaseImpl(get()) }
    single<UpdateMemesDataBaseUseCase> { UpdateMemesDataBaseUseCaseImpl(get()) }
    single<UpdateMemeDetailsDataBaseUseCase> { UpdateMemeDetailsDataBaseUseCaseImpl(get()) }
}

val dataBaseModule = module {
    single { Room.databaseBuilder(get(), MemeRoomDataBaseImpl::class.java, DATA_BASE_NAME).build() }
    single { get<MemeRoomDataBaseImpl>().memeDao() }
}

private const val DATA_BASE_NAME = "memeDataBase"