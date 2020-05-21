package com.example.memeitupapp.ui.listmemes.model

import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.util.Result

class ListMemesModel(private val memesService: MemeService) : ListMemesContract.Model {

    override fun getMemes(): Result<List<Meme>> = memesService.getMemesFromApi()
}