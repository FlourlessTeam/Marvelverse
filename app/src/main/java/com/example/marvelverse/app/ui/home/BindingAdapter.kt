package com.example.marvelverse.app.ui.home

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.home.adapter.HomeAdapter
import com.example.nestedrecyclerview.ui.base.BaseAdapter

@BindingAdapter(value = ["app:imageUrl"])
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Log.d("BindingAdapter", "setImageUrl: $url")
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)

    }
}

@BindingAdapter(value = ["app:items"])
fun <T> bindRecyclerView(recyclerView: RecyclerView, dataState: DataState<T>?) {
    dataState?.let {
        if (dataState is DataState.Success) {
            (recyclerView.adapter as BaseAdapter<T>).setItems(dataState.data)
        }
    }
}

@BindingAdapter(value = ["app:nestedItems"])
fun bindNestedRecyclerView(recyclerView: RecyclerView, items: List<HomeItem>?) {
    items?.let {
        (recyclerView.adapter as HomeAdapter).addNestedItem(it as MutableList<HomeItem>)
    }
}
