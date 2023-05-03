package com.example.marvelverse.app.ui.creators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Creator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoreCreatorsViewModel : ViewModel() {
    private val repository = MarvelRepository
    private val _creatorsData = MutableLiveData<MutableList<Creator>>()
    val creatorsData: LiveData<MutableList<Creator>>
        get() = _creatorsData
    private val compositeDisposable = CompositeDisposable()


    fun getCreators() {
        val disposable = repository.searchCreators()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _creatorsData.postValue(it as MutableList<Creator>?) }, {})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}