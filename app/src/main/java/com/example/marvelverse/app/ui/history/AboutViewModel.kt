package com.example.marvelverse.app.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.About
import com.example.marvelverse.utilites.DataState

class AboutViewModel : BaseViewModel() {

    private val repository = MarvelRepository

    private val _currentItem = MutableLiveData<DataState<About>>()
    val currentItem: LiveData<DataState<About>>
        get() = _currentItem

    init {
        getAboutItems()
    }

    private fun getAboutItems() = _currentItem.postValue(DataState.Success(repository.getItems()))

}