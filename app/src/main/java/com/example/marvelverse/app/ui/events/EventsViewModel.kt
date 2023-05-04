package com.example.marvelverse.app.ui.events
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventsViewModel: ViewModel() {
    private val repository = MarvelRepository
    private val _eventsData = MutableLiveData<MutableList<Event>>()
    val eventData: LiveData<MutableList<Event>>
        get() = _eventsData
    private val compositeDisposable = CompositeDisposable()

    fun getEvent() {
        val disposable = repository.searchEvents().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _eventsData.postValue(it as MutableList<Event>?) }, {})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}