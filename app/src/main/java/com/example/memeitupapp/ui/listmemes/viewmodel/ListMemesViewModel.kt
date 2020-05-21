package com.example.memeitupapp.ui.listmemes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.ui.listmemes.model.ListMemesModel
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Result
import com.example.memeitupapp.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMemesViewModel(private val listMemesModel: ListMemesModel) : ViewModel(), ListMemesContract.ViewModel {
    private var mutableMainState: MutableLiveData<Event<Data<List<Meme>>>> = MutableLiveData()
    override val mainState: LiveData<Event<Data<List<Meme>>>> = mutableMainState

    override fun fetchMemes() = viewModelScope.launch {
        mutableMainState.postValue(Event(Data(responseType = Status.LOADING)))
        withContext(Dispatchers.IO) { listMemesModel.getMemes() }.let { result ->
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