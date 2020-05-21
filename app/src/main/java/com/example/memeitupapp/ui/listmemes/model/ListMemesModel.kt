package com.example.memeitupapp.ui.listmemes.model

import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeRepository
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.util.Result

class ListMemesModel : ListMemesContract.Model {
    private val memesRepository = MemeRepository()

    override fun getMemes(): Result<List<Meme>> = memesRepository.getMemesFromApi()
}