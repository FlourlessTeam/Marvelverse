package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.R
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.app.ui.base.BaseNestedRecyclerAdapter
import com.example.marvelverse.app.ui.search.utils.SearchItems
import com.example.marvelverse.domain.entities.SearchKeyword
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


@BindingAdapter(value = ["app:items"])
fun <T> bindRecyclerView(recyclerView: RecyclerView, state: DataState<T>?) {
    state?.let {
        if (state is DataState.Success) {
            (recyclerView.adapter as BaseAdapter<T>).setItems(state.data as MutableList<T>)

        }
    }
}

@BindingAdapter(value = ["app:nestedItems"])
fun <T> bindNestedRecyclerView(recyclerView: RecyclerView, items: DataState<T>?) {
    items?.let {
        if (items is DataState.Success) {
            (recyclerView.adapter as BaseNestedRecyclerAdapter<T>).addNestedItem(items.data as MutableList<T>)
        }
    }
}

@BindingAdapter("visibilityIfNotBlank")
fun setVisibilityIfNotBlank(view: View, text: String?) {
    if (text.isNullOrEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
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


@BindingAdapter(value = ["app:showWhenSearchError"])
fun <T> showWhenSearchError(view: View, items: SearchItems?) {
    if (items?.characters is DataState.Error ||
        items?.comics is DataState.Error ||
        items?.events is DataState.Error
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSearchSuccess"])
fun <T> showWhenSearchSuccess(view: View, items: SearchItems?) {
    if (items?.characters is DataState.Success ||
        items?.comics is DataState.Success ||
        items?.events is DataState.Success
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSearchLoading"])
fun <T> showWhenSearchLoading(view: View, items: SearchItems?) {
    if (items?.characters is DataState.Loading ||
        items?.comics is DataState.Loading ||
        items?.events is DataState.Loading
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSearchEmpty"])
fun <T> showWhenSearchEmpty(view: View, items: SearchItems?) {
    if (items?.characters is DataState.Empty &&
        items?.comics is DataState.Empty &&
        items?.events is DataState.Empty
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSearchInSearchSuggests"])
fun <T> showWhenSearchInSearchSuggests(view: View, items: SearchItems?) {
    if (items?.characters is DataState.ShowKeywordSuggests &&
        items.comics is DataState.ShowKeywordSuggests &&
        items.events is DataState.ShowKeywordSuggests
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("thumbnail")
fun ImageView.bindThumbNail(thumbnail: String) {
    thumbnail.let {
        val validUrl = thumbnail
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}

@BindingAdapter("app:ItemsToChip")
fun ChipGroup.itemsToChips(items: List<SearchKeyword>?) {
    items?.forEach {
        val c = Chip(context).apply {
        text = it.keyword
        isCloseIconVisible = true
        setTextColor(ContextCompat.getColor(context, R.color.black))
    }
        this.addView(c)
    }
}
