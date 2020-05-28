package com.example.memeitupapp.data.entity

import com.example.memeitupapp.util.DEFAULT_ID
import com.example.memeitupapp.util.DEFAULT_INT
import com.example.memeitupapp.util.DEFAULT_STRING

data class Meme(
    val id: Int = DEFAULT_ID,
    val image: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val rank: Int = DEFAULT_INT,
    val tags: String = DEFAULT_STRING
)