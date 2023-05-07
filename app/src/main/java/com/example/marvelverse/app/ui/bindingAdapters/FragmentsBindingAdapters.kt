package com.example.marvelverse.app.ui.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.comics.ComicAdapter

import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchFragment
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import com.example.nestedrecyclerview.ui.base.BaseAdapter


@BindingAdapter(value = ["app:items"])
fun setRecyclerStoriesItems(view: RecyclerView, items: MutableList<Story>?) {
//    val adapter = MoreStoriesAdapter()
//    view.adapter = adapter
//    adapter.submitList(items)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerCreatorsItems(view: RecyclerView, items: MutableList<Creator>?) {
//    val adapter = MoreCreatorsAdapter()
//    view.adapter = adapter
//    adapter.submitList(items)
}


fun <T, BA : BaseRecyclerAdapter<T, *>> RecyclerView.bindList(dataState: DataState<T>) {
    val myAdapter = adapter as BA
    dataState.let {
        if (dataState is DataState.Success) {
            myAdapter.submitList(dataState.data)
        }
    }
}

@BindingAdapter("bindFragmentList")
fun <T> RecyclerView.bindFragmentList(dataState: DataState<T>?) {
    dataState?.let {
        if (dataState is DataState.Success) {
            (adapter as BaseAdapter<T>).submitList(dataState.data)
        }
    }
}

@BindingAdapter("thumbnail")
fun ImageView.bindThumbNail(thumbnail: Thumbnail) {
    thumbnail.let {
        val validUrl = "${thumbnail.path}.${thumbnail.extension}"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}

@BindingAdapter("showIfLoading")
fun <T> ProgressBar.showIfLoading(dataState: DataState<T>?) {
    dataState.let {
        visibility = if (dataState is DataState.Loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("showIfError")
fun <T> ImageView.showIfError(dataState: DataState<T>?) {
    dataState.let {
        visibility = if (dataState is DataState.Error) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter(value = ["app:items" , "app:listner" , "app:setUpRecyclerByData"])
fun RecyclerView.setRecyclerAdapter(
    items:List<*>?,
    listener:BaseInteractionListener,
    filterOption: SearchFilter?
) {
    Log.d("TAG" , "go now ${filterOption} , ${items?.size}")
    if(items!= null){
        when (filterOption!!) {
            SearchFilter.Character -> {
                val adapter = CharactersAdapter(listener)
                adapter.setItems(items as List<Character>)
                this.adapter = adapter
            }
            SearchFilter.Comic -> {
                val adapter = ComicAdapter(listener)
                adapter.setItems((items as List<Comic>))
                this.adapter = adapter
            }
            SearchFilter.Event -> {
                val adapter = EventAdapter(listener)
                adapter.setItems((items as List<Event>))
                this.adapter = adapter
            }

            else -> {}
        }
    }
}

@BindingAdapter(value = ["app:onTextChange"])
fun SearchView.searchViewListener(viewModel: SearchViewModel) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (!newText.isNullOrEmpty())
                when (viewModel.searchFilterOption.value!!) {
                    SearchFilter.Character -> viewModel.characterSearch(null, newText)
                    SearchFilter.Comic -> viewModel.comicSearch(null, newText)
                    SearchFilter.Event -> viewModel.eventSearch(null, newText)
                }
            return true
        }
    })
}

@BindingAdapter(value = ["app:showBottomSheet"])
fun showBottomSheet(view: View, listener: BottomSheetListener) {
    view.setOnClickListener {
        BottomSheetFragment(listener).show(
            it.findFragment<SearchFragment>().childFragmentManager,
            "TAG"
        )
    }
}

@BindingAdapter(value = ["app:clearWhenOptionChanged"])
fun SearchView.clearWhenOptionChanged(searchFilter: SearchFilter){
    this.setQuery("" , true)
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: DataState<T>?) {
    if (state is DataState.Error) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: DataState<T>?) {
    if (state is DataState.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: DataState<T>?) {
    if (state is DataState.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


