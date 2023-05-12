package com.example.marvelverse.app.ui.series


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Series

class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private var _series = MutableLiveData<DataState<Series>>()
    val series: LiveData<DataState<Series>> get() = _series

    private val _seriesEvent = MutableLiveData<SeriesEvent>()
    val seriesEvent: LiveData<SeriesEvent> get() = _seriesEvent

    init {
        getSeries()
    }

    private fun getSeries() {
        _series.postValue(DataState.Loading)
        MarvelRepository.searchSeries()
            .applySchedulers()
            .subscribe(
                {
                    _series.postValue(DataState.Success(it))
                },
                {
                    _series.postValue(DataState.Error(it))
                })
            .addTo(disposables)
    }

    override fun onSeriesClick(series: Series) {
        _seriesEvent.postValue(SeriesEvent.ClickSeriesEvent(series))
    }

    fun backToHome() {
        _seriesEvent.postValue(SeriesEvent.BackToHome)
    }



    fun clearEvents() {
        if (_seriesEvent.value != SeriesEvent.ReadyState)
            _seriesEvent.postValue(SeriesEvent.ReadyState)

    }

}


