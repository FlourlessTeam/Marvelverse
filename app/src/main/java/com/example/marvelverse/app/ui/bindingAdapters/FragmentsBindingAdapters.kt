package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.series.SeriesAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


@BindingAdapter( value=["app:items"])
fun setRecyclerStoriesItems(view:RecyclerView, items:MutableList<Story>?){
    if(items!=null){
        (view.adapter as MoreStoriesAdapter).submitList(items)
    }
    else{
        (view.adapter as MoreStoriesAdapter).submitList(emptyList())
    }
}

@BindingAdapter( value=["app:items"])
fun setRecyclerCreatorsItems(view:RecyclerView, items:MutableList<Creator>?){
    if(items!=null){
        (view.adapter as MoreCreatorsAdapter).submitList(items)
    }
    else{
        (view.adapter as MoreCreatorsAdapter).submitList(emptyList())
    }
}

@BindingAdapter(value = ["thumbnail"], requireAll = true)
fun ImageView.bindImageUrl(thumbnail: Thumbnail?) {
    thumbnail?.let {
        val imageUrlSecure = thumbnail.path.replace("http", "https")
        val validUrl = "$imageUrlSecure.${thumbnail.extension}"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}


@BindingAdapter("bindCharactersList")
fun <T> RecyclerView.bindCharactersList(dataState: DataState<T>?) {
    dataState.let {
        if(dataState is DataState.Success) {
            (adapter as CharactersAdapter).submitList((dataState.data as List<Character>?) ?: emptyList())
        }
    }
}

@BindingAdapter("bindSeriesList")
fun <T> RecyclerView.bindSeriesList(dataState: DataState<T>?) {
    if(dataState is DataState.Success) {
        (adapter as SeriesAdapter).submitList((dataState.data as List<Series>?) ?: emptyList())
    }}

@BindingAdapter("showIfLoading")
fun <T> ProgressBar.showIfLoading(dataState: DataState<T>?) {
    dataState?.let {
        visibility = if (dataState is DataState.Loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("showIfError")
fun <T> ImageView.showIfError(dataState: DataState<T>?) {
    dataState?.let {
        visibility = if (dataState is DataState.Error) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}