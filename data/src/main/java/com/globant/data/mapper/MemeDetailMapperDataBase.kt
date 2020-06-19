package com.globant.data.mapper

import com.globant.domain.entity.MemeDetail
import com.globant.domain.entity.MemeDetailRoom

class MemeDetailMapperDataBase : BaseMapper<MemeDetailRoom, MemeDetail> {
    override fun transformMeme(type: MemeDetailRoom): MemeDetail =
        MemeDetail(
            type.id,
            type.origin,
            type.image,
            type.name,
            type.rank,
            type.tags
        )

    fun transformToData(type: MemeDetail): MemeDetailRoom =
        MemeDetailRoom(
            type.ID,
            type.origin,
            type.image,
            type.name,
            type.rank,
            type.tags
        )
}
