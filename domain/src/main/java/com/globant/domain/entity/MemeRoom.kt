package com.globant.domain.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
class MemeRoom(
    @PrimaryKey val ID: Int,
    val image: String,
    val name: String,
    val rank: Int
)