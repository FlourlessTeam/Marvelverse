package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.creators.MoreCreatorsListener
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchFragment
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.app.ui.series.SeriesAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesListener
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

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

@BindingAdapter(value = ["thumbnail"])
fun ImageView.bindImageUrl(thumbnail: Thumbnail) {
    thumbnail.let {
        val imageUrlSecure = thumbnail.path.replace("http", "https")
        val validUrl = "$imageUrlSecure.${thumbnail.extension}"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}


@BindingAdapter("bindCharactersList")
fun RecyclerView.bindCharactersList(dataState: DataState<Character>?) {
    dataState.let {
        if (dataState is DataState.Success) {
            (adapter as CharactersAdapter).submitList(dataState.data)
        }
    }
}

@BindingAdapter("bindSeriesList")
fun RecyclerView.bindSeriesList(dataState: DataState<Series>?) {
    if (dataState is DataState.Success) {
        (adapter as SeriesAdapter).submitList(dataState.data)
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
@BindingAdapter(value = ["app:setUpRecyclerByData"])
fun RecyclerView.setRecyclerAdapter(FilterOption: SearchFilter?) {
    when (FilterOption!!) {
        SearchFilter.Character -> {

        }
        SearchFilter.Comic -> {

        }
        SearchFilter.Event -> {

        }
        SearchFilter.Creator -> {

        }
    }
}

@BindingAdapter(value = ["app:onTextChange"])
fun SearchView.searchViewListener(viewModel: SearchViewModel) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (!newText.isNullOrEmpty())
                when (viewModel.searchFilterOption.value!!) {
                    SearchFilter.Character -> viewModel.characterSearch(null, newText)
                    SearchFilter.Comic -> viewModel.comicSearch(null, newText)
                    SearchFilter.Event -> viewModel.eventSearch(null, newText)
                    SearchFilter.Creator -> viewModel.creatorSearch(null, newText)
                }
            return true
        }
    })
}

@BindingAdapter(value = ["app:showBottomSheet"])
fun showBottomSheet(view: View, listener: BottomSheetListener) {
    view.setOnClickListener {
        BottomSheetFragment(listener).show(
            it.findFragment<SearchFragment>().childFragmentManager,
            "TAG"
        )
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


