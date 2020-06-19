package com.example.memeitupapp.ui.memedetail.model

import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.globant.domain.usecase.GetMemeByIdFromDataBaseUseCase
import com.globant.domain.usecase.GetMemeByIdUseCase
import com.globant.domain.usecase.UpdateMemeDetailsDataBaseUseCase
import com.globant.domain.util.Result

class MemeDetailModel(
    private val getMemeByIdUseCase: GetMemeByIdUseCase,
    private val updateMemeDetailsDataBaseUseCase: UpdateMemeDetailsDataBaseUseCase,
    private val getMemeByIdFromDataBaseUseCase: GetMemeByIdFromDataBaseUseCase
) : MemesDetailsContract.Model {

    override fun getMeme(memeId: Int): Result<MemeDetail> =
        getMemeByIdUseCase.invoke(memeId).run {
            if (this is Result.Success) {
                updateMemeDetailsDataBaseUseCase.invoke(data)
                this
            } else {
                getMemeByIdFromDataBaseUseCase.invoke(memeId)
            }
        }
}
