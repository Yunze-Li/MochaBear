package com.arctos.mochabear.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {

    // not visible from outside
    private val liveData = MutableLiveData<Pair<Int, Int>>()

    // using Transformations.switchMap() to convert a LiveData object (internal) into another LiveData which is visible to outside
    val resultLiveData: LiveData<Int> = Transformations.switchMap(liveData) {
        Repository.fetchData(it.first, it.second)
    }

    fun fetchResult(input1: Int, input2: Int) {
        liveData.value = Pair(input1, input2)
    }
}