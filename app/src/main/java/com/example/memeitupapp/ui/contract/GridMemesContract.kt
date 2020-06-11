package com.example.memeitupapp.ui.contract

import androidx.lifecycle.LiveData
import com.globant.domain.entity.Meme
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.globant.domain.util.Result
import kotlinx.coroutines.Job

interface GridMemesContract {
    interface ViewModel {
        fun getLiveData(): LiveData<Event<Data<List<Meme>>>>
        fun fetchMemes(): Job
    }

    interface Model {
        fun getMemes(): Result<List<Meme>>
    }
}