package com.example.marvelverse.app.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository

class AboutViewModel: ViewModel() {

    private val repository = MarvelRepository

    private val _currentItem = MutableLiveData<List<String>>()
    val currentItem: LiveData<List<String>>
        get() = _currentItem

    init {
        getAboutItems()
    }

    private fun getAboutItems() {
        _currentItem.postValue(repository.getItems())
    }

}