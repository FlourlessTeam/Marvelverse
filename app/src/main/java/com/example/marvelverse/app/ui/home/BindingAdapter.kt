package com.example.marvelverse.app.ui.home

import android.util.Log
import android.view.View
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
fun <T>bindRecyclerView(recyclerView: RecyclerView, state: DataState<T>?) {
    state?.let {
        if (state is DataState.Success) {
            (recyclerView.adapter as BaseAdapter<T>).setItems(state.data as MutableList<T>)

        }
    }
}
@BindingAdapter(value = ["app:nestedItems"])
fun bindNestedRecyclerView(recyclerView: RecyclerView, items: DataState<HomeItem>?) {
    items?.let {
        if (items is DataState.Success) {
            (recyclerView.adapter as HomeAdapter).addNestedItem(items.data as MutableList<HomeItem>)
        }
    }
}

@BindingAdapter("availableItemsVisibility")
fun setAvailableItemsVisibility(view: View, state: DataState<*>?) {
    val availableItemCount = state?.let { it.toData()?.size ?: 0 } ?: 0
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


