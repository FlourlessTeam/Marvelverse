package com.example.marvelverse.app.ui.home

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
fun <T>bindRecyclerView(recyclerView: RecyclerView, items: List<T>?) {
    items?.let {
        (recyclerView.adapter as BaseAdapter<T>).setItems(items)
    }
}