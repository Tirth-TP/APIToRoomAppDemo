package com.example.apitoroomappdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Tirth Patel.
 */
class RecyclerViewStateViewModel : ViewModel() {
    private val _scrollPosition = MutableLiveData<Int>()
    val scrollPosition: LiveData<Int> get() = _scrollPosition

    fun saveScrollPosition(position: Int) {
        _scrollPosition.value = position
    }
}