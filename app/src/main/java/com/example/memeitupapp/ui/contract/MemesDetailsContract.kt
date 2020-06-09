package com.example.memeitupapp.ui.contract

import androidx.lifecycle.LiveData
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.globant.domain.util.Result
import kotlinx.coroutines.Job

interface MemesDetailsContract {
    interface ViewModel {
        fun getLiveData(): LiveData<Event<Data<MemeDetail>>>
        fun fetchMeme(memeId: Int): Job
    }

    interface Model {
        fun getMeme(memeId: Int): Result<MemeDetail>
    }
}