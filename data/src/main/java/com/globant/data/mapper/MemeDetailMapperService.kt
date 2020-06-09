package com.globant.data.mapper

import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.data.service.response.MemeDetailResponse

class MemeDetailMapperService :
    com.globant.data.mapper.BaseMapper<MemeDetailResponse, MemeDetail> {

    override fun transformMeme(type: MemeDetailResponse): MemeDetail = MemeDetail(
        type.ID,
        type.detail,
        type.image,
        type.name,
        type.rank,
        type.tags
    )
}