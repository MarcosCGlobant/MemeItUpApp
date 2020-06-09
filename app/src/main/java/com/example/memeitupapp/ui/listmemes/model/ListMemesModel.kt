package com.example.memeitupapp.ui.listmemes.model

import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.globant.domain.service.MemeService
import com.globant.domain.util.Result

class ListMemesModel(private val memesService: MemeService) : ListMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}