package com.globant.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.data.mapper.MemeDetailMapperDataBase
import com.globant.data.mapper.MemeMapperDataBase
import com.globant.domain.entity.MemeRoom
import com.globant.domain.database.MemeRoomDataBase
import com.globant.domain.entity.Meme
import com.globant.domain.entity.MemeDetail
import com.globant.domain.entity.MemeDetailRoom
import com.globant.domain.util.Result
import java.lang.Exception

@Database(entities = [MemeRoom::class, MemeDetailRoom::class], version = 1)
abstract class MemeRoomDataBaseImpl : RoomDatabase(), MemeRoomDataBase {

    abstract fun memeDao(): MemeDao
    private val memesMapper = MemeMapperDataBase()
    private val memeDetailMapper = MemeDetailMapperDataBase()

    override fun getAllMemesFromDataBase(): Result<List<Meme>> =
        memeDao().getMemes().let {
            if (it.isNotEmpty()) {
                Result.Success(memesMapper.transform(it))
            } else {
                Result.Failure(Exception("Memes not Found"))
            }
        }


    override fun getMemeByIdFromDataBase(memeId: Int): Result<MemeDetail> =
        memeDao().getMemeById(memeId).let {
            if (it.isNotEmpty()) {
                Result.Success(memeDetailMapper.transformMeme(it.first()))
            } else
                Result.Failure(Exception("MemeDetail not found"))
        }


    override fun updateMemeDetailInDataBase(memeDetail: MemeDetail) {
        memeDao().insertMemeDetail(memeDetailMapper.transformToData(memeDetail))
    }

    override fun updateMemesInDataBase(listOfMeme: List<Meme>) {
        listOfMeme.forEach {
            memeDao().insertMeme(memesMapper.transformToData(it))
        }
    }
}