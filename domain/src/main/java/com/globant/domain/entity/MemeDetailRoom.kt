package com.globant.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.globant.domain.util.DEFAULT_ID
import com.globant.domain.util.DEFAULT_STRING

@Entity(tableName = "memeDetail_table")
class MemeDetailRoom(
    @PrimaryKey val id: Int = DEFAULT_ID,
    val origin: String = DEFAULT_STRING,
    val image: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val rank: String = DEFAULT_STRING,
    val tags: String = DEFAULT_STRING
)