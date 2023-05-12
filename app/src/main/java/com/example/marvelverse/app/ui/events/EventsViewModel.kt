package com.example.marvelverse.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Event

class EventsViewModel : BaseViewModel(), EventInteractionListener {


    private var _event = MutableLiveData<DataState<Event>>()
    val event: LiveData<DataState<Event>> get() = _event

    private val _eventsEvent = MutableLiveData<EventsEvent>()
    val eventsEvent: LiveData<EventsEvent> get() = _eventsEvent

    init {
        getEvent()
    }

    private fun getEvent() {
        _event.postValue(DataState.Loading)
        MarvelRepository.searchEvents().applySchedulers()
            .subscribe(
                {
                    _event.postValue(DataState.Success(it))
                },
                {
                    _event.postValue(DataState.Error(it))
                }
            ).addTo(disposables)
    }

    override fun onEventClick(event: Event) {
        _eventsEvent.postValue(EventsEvent.ClickEventsEvent(event))
    }

    fun backToHome() {
        _eventsEvent.postValue(EventsEvent.BackToHome)
    }

    fun clearEvents() {
        if (_eventsEvent.value != EventsEvent.ReadyState)
            _eventsEvent.postValue(EventsEvent.ReadyState)
    }


}