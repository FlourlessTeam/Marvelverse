package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.comics.ComicAdapter
import com.example.marvelverse.app.ui.events.EventsAdapter
import com.example.marvelverse.app.ui.series.SeriesAdapter
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


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

@BindingAdapter(value = ["thumbnail"])
fun ImageView.bindImageUrl(thumbnail: Thumbnail) {
    thumbnail.let {
        val imageUrlSecure = thumbnail.path.replace("http", "https")
        val validUrl = "$imageUrlSecure.${thumbnail.extension}"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}


@BindingAdapter("bindCharactersList")
fun RecyclerView.bindCharactersList(dataState: DataState<Character>?) {
    dataState.let {
        if (dataState is DataState.Success) {
            (adapter as CharactersAdapter).submitList(dataState.data)
        }
    }
}

@BindingAdapter("bindSeriesList")
fun RecyclerView.bindSeriesList(dataState: DataState<Series>?) {
    if (dataState is DataState.Success) {
        (adapter as SeriesAdapter).submitList(dataState.data)
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
@BindingAdapter("bindComicList")
fun RecyclerView.bindComicList(dataState: DataState<Comic>?) {
    if (dataState is DataState.Success) {
        (adapter as ComicAdapter).submitList(dataState.data)
    }
}

@BindingAdapter("bindEventList")
fun RecyclerView.bindEventList(dataState: DataState<Event>?) {
    if (dataState is DataState.Success) {
        (adapter as EventsAdapter).submitList(dataState.data)
    }
}
