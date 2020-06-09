package com.example.memeitupapp.ui.gridmemes.model

import com.globant.domain.entity.Meme
import com.globant.data.service.MemeService
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.globant.domain.util.Result

class GridMemesModel(private val memesService: com.globant.data.service.MemeService) : GridMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}