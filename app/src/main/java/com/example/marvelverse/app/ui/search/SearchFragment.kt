package com.example.marvelverse.app.ui.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.app.ui.base.BottomNavFragment
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.comics.ComicAdapter
import com.example.marvelverse.app.ui.events.EventsAdapter
import com.example.marvelverse.databinding.FragmentSearchBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.utilites.DataState
import com.google.android.material.chip.ChipGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchFragment : BottomNavFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.searchEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }
        viewModel.searchFilterOption.observe(viewLifecycleOwner){
            handleRecyclerAdapters()
        }
        onChangeSelectedChip()
        searchViewListener()

    }

    private fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ClickCharacterEvent -> navigateToDetails(event.character)
            is SearchEvent.ClickComicEvent -> navigateToDetails(event.comic)
            is SearchEvent.ClickEventEvent -> navigateToDetails(event.event)
            else -> {}
        }
        viewModel.clearEvents()
    }
    private fun <T> navigateToDetails(element: T) {
        when (element) {
            is Character -> findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsCharacterFragment(
                    element
                )
            )

            is Comic -> findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToComicDetailsFragment(
                    element
                )
            )

            is Event -> findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToEventDetailsFragment(
                    element
                )
            )

            else -> {}
        }

    }


    private fun handleRecyclerAdapters(
    ) {
        val charactersAdapter = CharactersAdapter(viewModel)
        val comicAdapter = ComicAdapter(viewModel)
        val eventsAdapter = EventsAdapter(viewModel)
        when (viewModel.searchFilterOption.value!!) {
            SearchFilter.Character -> {
                setupRecyclerView(viewModel.characterList, charactersAdapter)
            }

            SearchFilter.Comic -> {
                setupRecyclerView(viewModel.comicList, comicAdapter)
            }

            SearchFilter.Event -> {
                setupRecyclerView(viewModel.eventList, eventsAdapter)
            }

            else -> {}

        }
    }

    private fun <T> setupRecyclerView(items: LiveData<DataState<T>>, adapter: BaseAdapter<T>) {
        val recyclerView = binding.recyclerView
        items.observe(viewLifecycleOwner){
            if (it is DataState.Success) {
                adapter.setItems(it.toData()!!)
                recyclerView.adapter = adapter
            }
        }
    }
    private fun onChangeSelectedChip() {
        binding.chipGroupSearchOption.setOnCheckedStateChangeListener{ group, checkedIds ->
            val selectedSearchFilter = when (group.checkedChipId) {
                binding.chipCharacter.id -> SearchFilter.Character
                binding.chipComics.id -> SearchFilter.Comic
                binding.chipEvent.id -> SearchFilter.Event
                else -> SearchFilter.Character
            }
            viewModel.onSearchFilterOptionSelected(selectedSearchFilter)

        }
    }
    private fun searchViewListener() {
        val observable = Observable.create<String> { emitter ->
            binding.searchViewLayout.editText?.doOnTextChanged { text, start, before, count ->
                emitter.onNext(text.toString())
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(1, TimeUnit.SECONDS)
            .subscribe { text ->
                if (text.isNotEmpty()) {
                    when (viewModel.searchFilterOption.value!!) {
                        SearchFilter.Character -> viewModel.characterSearch(null, text)
                        SearchFilter.Comic -> viewModel.comicSearch(null, text)
                        SearchFilter.Event -> viewModel.eventSearch(null, text)
                    }
                }
            }
    }

}