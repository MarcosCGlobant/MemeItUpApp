package com.example.memeitupapp.ui.memedetail.model

import com.globant.domain.entity.MemeDetail
import com.globant.data.service.MemeService
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.globant.domain.util.Result

class MemeDetailModel(private val memesService: com.globant.data.service.MemeService) : MemesDetailsContract.Model {

    override fun getMeme(memeId: Int): Result<MemeDetail> = memesService.getMemeByIdFromApi(memeId)
}