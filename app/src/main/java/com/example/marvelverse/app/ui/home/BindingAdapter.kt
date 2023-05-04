package com.example.marvelverse.app.ui.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.app.ui.home.adapter.HomeAdapter
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseNestedRecyclerAdapter

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
fun <T>bindRecyclerView(recyclerView: RecyclerView, items: List<T>?) {
    items?.let {
        (recyclerView.adapter as BaseAdapter<T>).setItems(items)
    }
}
@BindingAdapter(value = ["app:nestedItems"])
fun bindNestedRecyclerView(recyclerView: RecyclerView, items: List<HomeItem>?) {
    items?.let {
        (recyclerView.adapter as HomeAdapter).addNestedItem(it as MutableList<HomeItem>)
    }
}
