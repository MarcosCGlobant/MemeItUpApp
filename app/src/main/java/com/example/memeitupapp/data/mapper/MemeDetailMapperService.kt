package com.example.memeitupapp.data.mapper

import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.data.service.response.MemeDetailResponse

class MemeDetailMapperService : BaseMapper<MemeDetailResponse, MemeDetail> {

    override fun transformMeme(type: MemeDetailResponse): MemeDetail = MemeDetail(
        type.ID,
        type.detail,
        type.image,
        type.name,
        type.rank,
        type.tags
    )
}