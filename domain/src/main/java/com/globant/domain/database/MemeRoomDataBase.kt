package com.globant.domain.database

import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.domain.util.Result

interface MemeRoomDataBase {
    fun getAllMemesFromDataBase(): Result<List<Meme>>
    fun getMemeByIdFromDataBase(memeId: Int): Result<MemeDetail>
    fun updateMemesInDataBase(listOfMeme: List<Meme>)
    fun updateMemeDetailInDataBase(memeDetail: MemeDetail)
}