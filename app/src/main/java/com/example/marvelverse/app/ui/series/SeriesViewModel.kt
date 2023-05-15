package com.example.marvelverse.app.ui.series


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.utilites.SingleEventState

class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private var _series = MutableLiveData<DataState<Series>>()
    val series: LiveData<DataState<Series>> get() = _series

    private val _seriesEvent = MutableLiveData<SingleEventState<SeriesEvent>>()
    val seriesEvent: LiveData<SingleEventState<SeriesEvent>> get() = _seriesEvent

    init {
        getSeries()
    }

    private fun getSeries() {
        _series.postValue(DataState.Loading)
        MarvelRepository.searchSeries().subscribeBy(::onSeriesSuccess, ::onSeriesError)
    }
    fun onSeriesSuccess(it: List<Series>) {
        if (it.isEmpty()) {
            _series.postValue(DataState.Empty)
        } else {
            _series.postValue(DataState.Success(it))
        }
    }
    fun onSeriesError(it: Throwable) {
        _series.postValue(DataState.Error(it))
    }

    override fun onSeriesClick(series: Series) {
        _seriesEvent.postValue(SingleEventState(SeriesEvent.ClickSeriesEvent(series)))
    }



}


