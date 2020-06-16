package com.globant.domain.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
class MemeRoom(
    @PrimaryKey @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rank") val rank: Int
)