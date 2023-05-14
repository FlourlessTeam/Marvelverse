package com.example.marvelverse.app.ui.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.app.ui.base.BaseNestedRecyclerAdapter
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchItems
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


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

@BindingAdapter("visibilityIfNoItems")
fun setVisibilityIfNoItems(view: View, availableItemCount: Int) {
    view.visibility = if (availableItemCount == 0) View.GONE else View.VISIBLE
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
    if (items?.characters is DataState.NoResult &&
        items?.comics is DataState.NoResult &&
        items?.events is DataState.NoResult
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
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
