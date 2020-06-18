package com.globant.data.mapper

import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeRoom

class MemeMapperDataBase : BaseMapper<MemeRoom, Meme> {
    override fun transformMeme(type: MemeRoom): Meme =
            Meme(type.ID, type.image, type.name, type.rank)

    fun transform(memes: List<MemeRoom>): List<Meme> =
            memes.map { transformMeme(it) }

    override fun transformToData(type: Meme): MemeRoom =
            MemeRoom(type.id, type.image, type.name, type.rank)
}