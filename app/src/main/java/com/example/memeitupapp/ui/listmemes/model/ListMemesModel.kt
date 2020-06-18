package com.example.memeitupapp.ui.listmemes.model

import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.usecase.UpdateMemesDataBaseUseCase
import com.globant.domain.util.Result

class ListMemesModel(
    private val getMemesUseCase: GetMemesUseCase,
    private val updateMemesDataBaseUseCase: UpdateMemesDataBaseUseCase,
    private val getMemesFromDataBaseUseCase: GetMemesFromDataBaseUseCase
) : ListMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> =
        getMemesUseCase.invoke().run {
            if (this is Result.Success) {
                updateMemesDataBaseUseCase.invoke(data)
                this
            } else {
                getMemesFromDataBaseUseCase.invoke()
            }
        }
}