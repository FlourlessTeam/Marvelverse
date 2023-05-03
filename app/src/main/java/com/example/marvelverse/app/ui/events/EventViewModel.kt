package com.example.marvelverse.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.domain.entities.main.Event

class EventViewModel : ViewModel() {
	private val _event = MutableLiveData<Event>()
	val event: LiveData<Event>
		get() = _event


}