package com.example.marvelverse.app.ui.bindingAdapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.comics.ComicAdapter
import com.example.marvelverse.app.ui.events.EventsAdapter
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.app.ui.base.BaseNestedRecyclerAdapter
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchFragment
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit



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


@BindingAdapter(value = ["app:items", "app:listener", "app:setUpRecyclerByData"])
fun RecyclerView.setRecyclerAdapter(
    items: List<*>?,
    listener: BaseInteractionListener,
    filterOption: SearchFilter?,
) {
    if (items != null) {
        when (filterOption!!) {
            SearchFilter.Character -> {
                val adapter = CharactersAdapter(listener)
                adapter.setItems(items as List<Character>)
                this.adapter = adapter
            }

            SearchFilter.Comic -> {
                val adapter = ComicAdapter(listener)
                adapter.setItems((items as List<Comic>))
                this.adapter = adapter
            }

            SearchFilter.Event -> {
                val adapter = EventsAdapter(listener)
                adapter.setItems((items as List<Event>))
                this.adapter = adapter
            }

            else -> {}
        }
    }
}

@BindingAdapter(value = ["app:onTextChange"])
fun SearchView.searchViewListener(viewModel: SearchViewModel) {

    val observable = Observable.create<String> { emitter ->
        this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    emitter.onNext(newText)
                }
                return true
            }
        })
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .debounce(1, TimeUnit.SECONDS)
        .subscribe { text ->
            if (text.isNotEmpty()) {
                when (viewModel.searchFilterOption.value!!) {
                    SearchFilter.Character -> viewModel.characterSearch(null, text)
                    SearchFilter.Comic -> viewModel.comicSearch(null, text)
                    SearchFilter.Event -> viewModel.eventSearch(null, text)
                }
            } else {
                viewModel.setItemListStateEmpty()
            }
        }


}

@SuppressLint("CheckResult")
@BindingAdapter(value = ["app:showBottomSheet", "app:selectedFilterOption"])
fun showBottomSheet(view: View, listener: BottomSheetListener, selectedOption: SearchFilter) {
    val clickSubject = PublishSubject.create<Unit>()

    view.setOnClickListener {
        clickSubject.onNext(Unit)
    }

    clickSubject.throttleFirst(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            BottomSheetFragment(listener, selectedOption).show(
                view.findFragment<SearchFragment>().childFragmentManager,
                "TAG"
            )
        }
}

@BindingAdapter(value = ["app:clearWhenOptionChanged"])
fun SearchView.clearWhenOptionChanged(searchFilter: SearchFilter) {
    this.setQuery("", true)
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


@BindingAdapter(value = ["app:hideWhenEmpty"])
fun <T> hideWhenEmpty(view: View, state: DataState<T>?) {
    if (state is DataState.Empty) {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenEmpty"])
fun <T> showWhenEmpty(view: View, state: DataState<T>?) {
    if (state is DataState.Empty || (state)?.toData()?.isEmpty() == true) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


@BindingAdapter("availableItemsVisibility")
fun setAvailableItemsVisibility(view: View, state: DataState<*>?) {
    val availableItemCount = state?.let { it.toData()?.size ?: 0 } ?: 0
    view.visibility = if (availableItemCount > 0) View.VISIBLE else View.GONE
}

@BindingAdapter("visibilityIfNoItems")
fun setVisibilityIfNoItems(view: View, availableItemCount: Int) {
    view.visibility = if (availableItemCount == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("visibilityIfNotBlank")
fun setVisibilityIfNotBlank(view: View, text: String?) {
    if (text.isNullOrEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
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
