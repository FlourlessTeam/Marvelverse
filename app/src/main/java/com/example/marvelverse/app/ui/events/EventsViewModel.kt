package com.example.marvelverse.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.utilites.SingleEventState

class EventsViewModel : BaseViewModel(), EventInteractionListener {


    private var _event = MutableLiveData<DataState<Event>>()
    val event: LiveData<DataState<Event>> get() = _event

    private val _eventsEvent = MutableLiveData<SingleEventState<EventsEvent>>()
    val eventsEvent: LiveData<SingleEventState<EventsEvent>> get() = _eventsEvent

    init {
        getEvent()
    }

    private fun getEvent() {
        _event.postValue(DataState.Loading)
        MarvelRepository.searchEvents().subscribeBy(::onEventSuccess, ::onEventError)
    }
    fun onEventSuccess(it: List<Event>) {
        if (it.isEmpty()) {
            _event.postValue(DataState.Empty)
        } else {
            _event.postValue(DataState.Success(it))
        }
    }
    fun onEventError(it: Throwable) {
        _event.postValue(DataState.Error(it))
    }

    override fun onEventClick(event: Event) {
        _eventsEvent.postValue(SingleEventState(EventsEvent.ClickEventsEvent(event)))
    }



}