package com.example.marvelverse.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventViewModel : ViewModel() {

	private val compositeDisposable by lazy { CompositeDisposable() }
	private val _character: MutableLiveData<List<Character>?> = MutableLiveData()
	private val _comics: MutableLiveData<List<Comic>?> = MutableLiveData()
	private val _series: MutableLiveData<List<Series>?> = MutableLiveData()
	val comics: LiveData<List<Comic>?>
		get() = _comics
	val series: LiveData<List<Series>?>
		get() = _series

	val character: LiveData<List<Character>?>
		get() = _character

	fun getCharacters(url: String) {
		val single = MarvelRepository.getCharactersByUrl(url).subscribeOn(Schedulers.io())
		val disposable = single.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				_character.postValue(it)
			}, {
			})

		compositeDisposable.add(disposable)
	}

	fun getComics(url: String) {
		val single = MarvelRepository.getComicsByUrl(url).subscribeOn(Schedulers.io())
		val disposable = single.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				_comics.postValue(it)
			}, {
			})

		compositeDisposable.add(disposable)
	}

	fun getSeries(url: String) {
		val single = MarvelRepository.getSeriesByUrl(url).subscribeOn(Schedulers.io())
		val disposable = single.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				_series.postValue(it)
			}, {
			})

		compositeDisposable.add(disposable)
	}

	override fun onCleared() {
		compositeDisposable.clear()
	}
}