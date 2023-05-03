package com.example.marvelverse.app.ui.viewAll

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel:ViewModel() {
    private val repository=MarvelRepository

    private val _storiesData= MutableLiveData<MutableList<Story>>()
    val storiesData:LiveData<MutableList<Story>>
        get()= _storiesData


    private val _creatorsData= MutableLiveData<MutableList<Creator>>()
    val creatorsData:LiveData<MutableList<Creator>>
        get()= _creatorsData

    @SuppressLint("CheckResult")
    fun  getStories(){
            repository.searchStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _storiesData.postValue(it as MutableList<Story>?)
                },
                {
                })
    }

    @SuppressLint("CheckResult")
    fun  getCreators(){
        repository.searchCreators()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _creatorsData.postValue(it as MutableList<Creator>?)
            },
            {
            })
    }

}