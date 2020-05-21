package com.example.memeitupapp.ui.contract

import androidx.lifecycle.LiveData
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Result
import kotlinx.coroutines.Job

interface GridMemesContract {
    interface ViewModel {
        val mainState: LiveData<Event<Data<List<Meme>>>>
        fun fetchMemes(): Job
    }

    interface Model {
        fun getMemes(): Result<List<Meme>>
    }
}