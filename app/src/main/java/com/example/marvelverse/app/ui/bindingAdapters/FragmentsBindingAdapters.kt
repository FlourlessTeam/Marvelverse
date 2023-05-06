package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.domain.entities.main.Creator
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