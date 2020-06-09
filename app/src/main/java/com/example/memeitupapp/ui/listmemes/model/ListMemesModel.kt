package com.example.memeitupapp.ui.listmemes.model

import com.globant.domain.entity.Meme
import com.globant.data.service.MemeService
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.globant.domain.util.Result

class ListMemesModel(private val memesService: com.globant.data.service.MemeService) : ListMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}