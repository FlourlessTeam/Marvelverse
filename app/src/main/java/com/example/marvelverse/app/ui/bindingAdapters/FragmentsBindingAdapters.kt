package com.example.marvelverse.app.ui.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchFragment
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story


@BindingAdapter(value = ["app:items"])
fun setRecyclerStoriesItems(view: RecyclerView, items: MutableList<Story>?) {
    val adapter = MoreStoriesAdapter()
    view.adapter = adapter
    adapter.submitList(items)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerCreatorsItems(view: RecyclerView, items: MutableList<Creator>?) {
    val adapter = MoreCreatorsAdapter()
    view.adapter = adapter
    adapter.submitList(items)
}

@BindingAdapter(value = ["app:setUpRecyclerByData"])
fun RecyclerView.setRecyclerAdapter(viewmodel: SearchViewModel) {
    viewmodel.searchFilterOption.observe(
        this.findFragment<SearchFragment>().viewLifecycleOwner
    ) { t ->
        when (t!!) {
            SearchFilter.Character -> {
                val adapter = MoreCreatorsAdapter()
                adapter.submitList(viewmodel.creator.value)
                this.adapter = adapter
            }
            SearchFilter.Comic -> {
                val adapter = MoreCreatorsAdapter()
                adapter.submitList(viewmodel.creator.value)
                this.adapter = adapter
            }
            SearchFilter.Event -> {
                val adapter = MoreCreatorsAdapter()
                adapter.submitList(viewmodel.creator.value)
                this.adapter = adapter
            }
            SearchFilter.Creator -> {
                val adapter = MoreCreatorsAdapter()
                adapter.submitList(viewmodel.creator.value)
                this.adapter = adapter
            }
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


