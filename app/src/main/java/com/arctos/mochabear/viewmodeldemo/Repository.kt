package com.arctos.mochabear.viewmodeldemo

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

object Repository {

    fun fetchData(arg1: Int, arg2: Int) = liveData(Dispatchers.Main) {
        emit(arg1 + arg2)
    }
}