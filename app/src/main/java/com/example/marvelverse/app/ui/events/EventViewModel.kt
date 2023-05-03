package com.example.marvelverse.app.ui.events

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class EventViewModel : ViewModel() {

	private val _character: MutableLiveData<List<Character>?> = MutableLiveData()
	private val _comics: MutableLiveData<List<Comic>?> = MutableLiveData()
	private val _series: MutableLiveData<List<Series>?> = MutableLiveData()
	val comics: LiveData<List<Comic>?>
		get() = _comics
	val series: LiveData<List<Series>?>
		get() = _series

	val character: LiveData<List<Character>?>
		get() = _character

	@SuppressLint("CheckResult")
	fun getCharacters(url: String) {
		MarvelRepository.getCharactersByUrl(url).subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				Log.i("FFF", it.toString())
				_character.postValue(it)
			}, {
				Log.i("FFF", it.message.toString())
			})
	}

	@SuppressLint("CheckResult")
	fun getComics(url: String) {
		MarvelRepository.getComicsByUrl(url).subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				_comics.postValue(it)
			}, {
			})
	}

	@SuppressLint("CheckResult")
	fun getSeries(url: String) {
		MarvelRepository.getSeriesByUrl(url).subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				_series.postValue(it)
			}, {
			})
	}
}