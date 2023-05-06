package com.example.marvelverse.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.home.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventsViewModel: ViewModel(), EventInteractionListener {

    private val compositeDisposable = CompositeDisposable()

    private var _event = MutableLiveData<DataState<Event>>()
    val event: LiveData<DataState<Event>> get() = _event

    private val _eventsEvent=MutableLiveData<EventsEvent>()
    val eventsEvent:LiveData<EventsEvent> get() = _eventsEvent

    init {
        getEvent()
    }

    private fun getEvent() {
        _event.postValue(DataState.Loading)
        MarvelRepository.searchEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _event.postValue(DataState.Success(it))
                },
                {
                    _event.postValue(DataState.Error(it))
                }
            ).addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onEventClick(event: Event) {
        _eventsEvent.postValue(EventsEvent.ClickEventsEvent(event))
    }
    fun backToHome(){
        _eventsEvent.postValue(EventsEvent.BackToHome)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}