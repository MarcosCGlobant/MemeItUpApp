package com.globant.domain.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.globant.domain.util.DEFAULT_ID
import com.globant.domain.util.DEFAULT_INT
import com.globant.domain.util.DEFAULT_STRING

@Entity(tableName = "meme_table")
class MemeRoom(
    @PrimaryKey val ID: Int = DEFAULT_ID,
    val image: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val rank: Int = DEFAULT_INT
)