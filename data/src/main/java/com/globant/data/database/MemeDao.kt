package com.globant.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.entity.MemeRoom

@Dao
interface MemeDao {

    @Query("SELECT * FROM meme_table")
    fun getMemes(): LiveData<List<MemeRoom>>

    @Query("SELECT * FROM memeDetail_table WHERE id = :memeId")
    fun getMemeById(memeId: Int): LiveData<MemeDetailRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeme(meme: MemeRoom)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMemeDetail(memeDetail: MemeDetailRoom)

}