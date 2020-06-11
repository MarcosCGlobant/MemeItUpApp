package com.example.memeitupapp.ui.gridmemes.model

import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.globant.domain.service.MemeService
import com.globant.domain.util.Result

class GridMemesModel(private val memesService: MemeService) : GridMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}