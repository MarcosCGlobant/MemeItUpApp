package com.globant.domain.database

import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.entity.MemeRoom

interface MemeRoomDataBase {
    fun getAllMemesFromDataBase(): List<MemeRoom>
    fun getMemeByIdFromDataBase(memeId: Int): MemeDetailRoom
    fun updateMemesInDataBase(listOfMeme: List<MemeRoom>)
    fun updateMemeDetailInDataBase(memeDetail: MemeDetailRoom)
}