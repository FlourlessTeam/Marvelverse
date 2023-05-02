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
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "seriesViewModel"

class SeriesViewModel : ViewModel(), SeriesAdapter.OnSeriesClickListener {
    private val compositeDisposable = CompositeDisposable()

    private var _series = MutableLiveData<DataState<List<Series>>>()
    val series: LiveData<DataState<List<Series>>> get() = _series

    init {
        getseries()
    }

    private fun getseries() {
        _series.postValue(DataState.Loading)
        MarvelRepository.searchSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _series.postValue(DataState.Success(it))
                },
                onError = {
                    _series.postValue(DataState.Error(it))
                }
            ).addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onClick(series: Series) {
        Log.d(TAG, "onClick: ${series.title}")
    }
}


