package com.globant.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.entity.MemeRoom

@Dao
interface MemeDao {

    @Query("SELECT * FROM meme_table")
    fun getMemes(): List<MemeRoom>

    @Query("SELECT * FROM memeDetail_table WHERE id = :memeId")
    fun getMemeById(memeId: Int): List<MemeDetailRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeme(meme: MemeRoom)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMemeDetail(memeDetail: MemeDetailRoom)

}
