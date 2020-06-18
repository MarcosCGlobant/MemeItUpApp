package com.example.memeitupapp.ui.gridmemes.model

import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.usecase.UpdateMemesDataBaseUseCase
import com.globant.domain.util.Result

class GridMemesModel(
    private val getMemesUseCase: GetMemesUseCase,
    private val getMemesFromDataBaseUseCase: GetMemesFromDataBaseUseCase,
    private val updateMemesDataBaseUseCase: UpdateMemesDataBaseUseCase
) : GridMemesContract.Model {

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