package com.example.marvelverse.app.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelverse.domain.entities.main.Comic
import kotlinx.coroutines.launch

class ComicsViewModel():ViewModel() {
    private val _comics = MutableLiveData<List<Comic>>()
    val comics: LiveData<List<Comic>> = _comics

    init {
        // Initialize the _comics LiveData
        viewModelScope.launch {
            _comics.value = getComicsFromRepository()
        }
    }

    private suspend fun getComicsFromRepository(): List<Comic> {
        // Retrieve the list of comics from the repository
        // and return it

        return TODO("Provide the return value")
    }
}