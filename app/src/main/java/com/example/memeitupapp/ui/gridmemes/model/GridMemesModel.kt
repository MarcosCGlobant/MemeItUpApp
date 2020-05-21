package com.example.memeitupapp.ui.gridmemes.model

import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeRepository
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.util.Result

class GridMemesModel : GridMemesContract.Model {
    private val memesRepository = MemeRepository()

    override fun getMemes(): Result<List<Meme>> = memesRepository.getMemesFromApi()
}