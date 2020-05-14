package com.example.memeitupapp.ui.memes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeRepository
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Result
import com.example.memeitupapp.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemeItUpViewModel : ViewModel() {

    private val memesRepository: MemeRepository = MemeRepository()
    private var mutableMainState: MutableLiveData<Event<Data<List<Meme>>>> = MutableLiveData()
    val mainState: LiveData<Event<Data<List<Meme>>>>
        get() = mutableMainState

    fun getMemesFromApi() = viewModelScope.launch {
        mutableMainState.postValue(Event(Data(responseType = Status.LOADING)))
        withContext(Dispatchers.IO) { memesRepository.getMemes() }.let { result ->
            when (result) {
                is Result.Failure -> {
                    mutableMainState.postValue(Event(Data(responseType = Status.GET_MEMES_ERROR, error = result.exception)))
                }
                is Result.Success -> {
                    mutableMainState.postValue(Event(Data(responseType = Status.GET_MEMES_SUCCESS, data = result.data)))
                }
            }
        }
    }
}