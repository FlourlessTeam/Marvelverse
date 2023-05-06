package com.example.marvelverse.app.ui.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

@BindingAdapter("availableItemsVisibility")
fun setAvailableItemsVisibility(view: View, availableItemCount: Int) {
    view.visibility = if (availableItemCount > 0) View.VISIBLE else View.GONE
}
@BindingAdapter("visibilityIfNotBlank")
fun setVisibilityIfNotBlank(view: View, text: String?) {
    if (text.isNullOrEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}


