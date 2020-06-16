package com.globant.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.domain.entity.MemeRoom
import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.MemeDetailRoom

@Database(entities = [MemeRoom::class, MemeDetailRoom::class], version = 1)
abstract class MemeRoomDataBaseImpl : RoomDatabase(), MemeRoomDataBase {

    abstract fun memeDao(): MemeDao

    override fun getAllMemesFromDataBase(): List<MemeRoom> {
        lateinit var listOfMemes: List<MemeRoom>
        memeDao().getMemes().value?.let { listOfMemes = it }
        return listOfMemes
    }

    override fun getMemeByIdFromDataBase(memeId: Int): MemeDetailRoom {
        lateinit var memeDetail: MemeDetailRoom
        memeDao().getMemeById(memeId).value?.let { memeDetail = it }
        return memeDetail
    }

    override fun updateMemeDetailInDataBase(memeDetail: MemeDetailRoom) {
        memeDao().insertMemeDetail(memeDetail)
    }

    override fun updateMemesInDataBase(listOfMeme: List<MemeRoom>) {
        listOfMeme.forEach {
            memeDao().insertMeme(it)
        }
    }
}