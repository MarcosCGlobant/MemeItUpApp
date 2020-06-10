package com.globant.data.mapper

import com.globant.domain.entity.Meme
import com.globant.data.service.response.MemeResponse

class MemesMapperService :
    BaseMapper<MemeResponse, Meme> {

    override fun transformMeme(type: MemeResponse): Meme = Meme(
        type.ID,
        type.image,
        type.name,
        type.rank
    )

    fun transform(memeResponse: List<MemeResponse>): List<Meme> = memeResponse.map { transformMeme(it) }
}