package com.example.memeitupapp.util

data class Data<RequestData>(var responseType: Status, var data: RequestData? = null, var error: Exception? = null)

enum class Status {
    LOADING,
    GET_MEMES_SUCCESS,
    GET_MEMES_ERROR
}