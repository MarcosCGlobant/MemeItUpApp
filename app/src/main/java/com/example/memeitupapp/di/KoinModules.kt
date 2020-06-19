package com.example.memeitupapp.di

import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.example.memeitupapp.ui.gridmemes.model.GridMemesModel
import com.example.memeitupapp.ui.gridmemes.viewmodel.GridMemesViewModel
import com.example.memeitupapp.ui.listmemes.model.ListMemesModel
import com.example.memeitupapp.ui.listmemes.viewmodel.ListMemesViewModel
import com.example.memeitupapp.ui.memedetail.model.MemeDetailModel
import com.example.memeitupapp.ui.memedetail.viewmodel.MemeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { GridMemesViewModel(get()) }
    viewModel { ListMemesViewModel(get()) }
    viewModel { MemeDetailViewModel(get()) }
}

val modelsModule = module {
    single<GridMemesContract.Model> { GridMemesModel(get(), get(), get()) }
    single<ListMemesContract.Model> { ListMemesModel(get(), get(), get()) }
    single<MemesDetailsContract.Model> { MemeDetailModel(get(), get(), get()) }
}
