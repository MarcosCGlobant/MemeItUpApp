package com.globant.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memeDetail_table")
class MemeDetailRoom(
    @PrimaryKey val id: Int,
    val origin: String,
    val image: String,
    val name: String,
    val rank: Int,
    val tags: String
)