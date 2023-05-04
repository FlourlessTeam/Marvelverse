package com.example.marvelverse.app.ui.bindingAdapters

import android.util.Log
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story


@BindingAdapter( value=["app:items"])
fun setRecyclerStoriesItems(view:RecyclerView, items:MutableList<Story>?){
    val adapter= MoreStoriesAdapter()
         view.adapter= adapter
         adapter.submitList(items)
}

@BindingAdapter( value=["app:items"])
fun setRecyclerCreatorsItems(view:RecyclerView, items:MutableList<Creator>?){
    val adapter= MoreCreatorsAdapter()
    view.adapter= adapter
    adapter.submitList(items)
}

@BindingAdapter( value=["app:setUpRecyclerByData"])
fun RecyclerView.recyclerAdapterSetup(viewmodel:SearchViewModel){
    when(viewmodel.searchFilterOption){
        SearchFilter.Character -> {

        }
        SearchFilter.Comic -> {

        }
        SearchFilter.Event -> {

        }
        SearchFilter.Creator -> {
            val adapter = MoreCreatorsAdapter()
            adapter.submitList(viewmodel.creator.value)
            this.adapter = adapter

        }
    }
}

@BindingAdapter( value=["app:onTextChange"] )
fun SearchView.searchViewListener(viewModel:SearchViewModel) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            when (viewModel.searchFilterOption) {
                SearchFilter.Character -> viewModel.characterSearch(null, newText)
                SearchFilter.Comic -> viewModel.comicSearch(null, newText)
                SearchFilter.Event -> viewModel.eventSearch(null, newText)
                SearchFilter.Creator -> viewModel.creatorSearch(null, newText)
            }
            return true
        }
    })
}

