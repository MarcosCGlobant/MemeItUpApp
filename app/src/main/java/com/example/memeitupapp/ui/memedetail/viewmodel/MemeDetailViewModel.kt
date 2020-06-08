package com.example.memeitupapp.ui.memedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.globant.domain.util.Result
import com.example.memeitupapp.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemeDetailViewModel(private val memeDetailModel: MemesDetailsContract.Model) : ViewModel(), MemesDetailsContract.ViewModel {
    private var mutableMainState: MutableLiveData<Event<Data<MemeDetail>>> = MutableLiveData()
    override fun getLiveData(): LiveData<Event<Data<MemeDetail>>> = mutableMainState

    override fun fetchMeme(memeId: Int) = viewModelScope.launch {
        mutableMainState.postValue(Event(Data(responseType = Status.LOADING)))
        withContext(Dispatchers.IO) { memeDetailModel.getMeme(memeId) }.let { result ->
            when (result) {
                is Result.Failure -> {
                    mutableMainState.postValue(Event(Data(responseType = Status.GET_MEME_BY_ID_ERROR, error = result.exception)))
                }
                is Result.Success -> {
                    mutableMainState.postValue(Event(Data(responseType = Status.GET_MEME_BY_ID_SUCCESS, data = result.data)))
                }
            }
        }
    }
}