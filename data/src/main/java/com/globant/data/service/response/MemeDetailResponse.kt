package com.globant.data.service.response

import com.globant.domain.util.DEFAULT_ID
import com.globant.domain.util.DEFAULT_INT
import com.globant.domain.util.DEFAULT_STRING

data class MemeDetailResponse(
    val ID: Int = DEFAULT_ID,
    val detail: String = DEFAULT_STRING,
    val image: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val rank: Int = DEFAULT_INT,
    val tags: String = DEFAULT_STRING
)