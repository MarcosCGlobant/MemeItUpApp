package com.example.memeitupapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object MemeViewModelFactory {
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
}