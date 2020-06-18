package com.globant.data.mapper

import com.globant.domain.entity.MemeDetail
import com.globant.data.service.response.MemeDetailResponse

class MemeDetailMapperService : BaseMapper<MemeDetailResponse, MemeDetail> {

    override fun transformMeme(type: MemeDetailResponse): MemeDetail = MemeDetail(
            type.ID,
            type.detail,
            type.image,
            type.name,
            type.rank,
            type.tags
    )

    override fun transformToData(type: MemeDetail): MemeDetailResponse = MemeDetailResponse(
            type.ID,
            type.origin,
            type.image,
            type.name,
            type.rank,
            type.tags
    )
}