package com.example.marvelverse.app.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Series
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "seriesViewModel"

class SeriesViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var _series = MutableLiveData<DataState<Series>>()
    val series: LiveData<DataState<Series>> get() = _series

    init {
        getSeries()
    }

    private fun getSeries() {
        _series.postValue(DataState.Loading)
        MarvelRepository.searchSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _series.postValue(DataState.Success(it))
                    Log.d("xxxx", it.toString())
                },
                {
                    _series.postValue(DataState.Error(it))
                    Log.d("xxxx", it.toString()+"Wwwwwww")
                })
            .addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onClick(series: Series) {
        Log.d(TAG, "onClick: ${series.title}")
    }
}


