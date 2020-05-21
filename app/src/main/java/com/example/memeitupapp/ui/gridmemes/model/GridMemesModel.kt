package com.example.memeitupapp.ui.gridmemes.model

import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.util.Result

class GridMemesModel(private val memesService: MemeService) : GridMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}