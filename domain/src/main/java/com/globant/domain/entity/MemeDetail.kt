package com.globant.domain.entity

import com.globant.domain.util.DEFAULT_ID
import com.globant.domain.util.DEFAULT_INT
import com.globant.domain.util.DEFAULT_STRING

data class MemeDetail(
    val ID: Int = DEFAULT_ID,
    val origin: String = DEFAULT_STRING,
    val image: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val rank: Int = DEFAULT_INT,
    val tags: String = DEFAULT_STRING
)