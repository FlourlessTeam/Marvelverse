package com.example.marvelverse.app.ui.bindingAdapters

import android.view.View
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.creators.MoreCreatorsListener
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.app.ui.search.SearchFragment
import com.example.marvelverse.app.ui.search.SearchViewModel
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesListener
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story


@BindingAdapter(value = ["app:items"])
fun setRecyclerStoriesItems(view: RecyclerView, items: MutableList<Story>?) {
    val adapter = MoreStoriesAdapter(object :MoreStoriesListener{
        override fun onClick(story: Story) {

        }

    })
    view.adapter = adapter
    adapter.submitList(items)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerCreatorsItems(view: RecyclerView, items: MutableList<Creator>?) {
    val adapter = MoreCreatorsAdapter(object :MoreCreatorsListener{
        override fun onClick(creator: Creator) {

        }
    })
    view.adapter = adapter
    adapter.submitList(items)
}

@BindingAdapter(value = ["app:setUpRecyclerByData"])
fun RecyclerView.setRecyclerAdapter(viewModel: SearchViewModel) {
    viewModel.searchFilterOption.observe(
        this.findFragment<SearchFragment>().viewLifecycleOwner
    ) { t ->
        when (t!!) {
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

@BindingAdapter(value = ["app:showWhenError"])
fun showWhenError(view: View, viewModel: SearchViewModel) {
    viewModel.searchFilterOption.observe(
        view.findFragment<SearchFragment>().viewLifecycleOwner
    ) { t ->
        when (t!!) {
            SearchFilter.Character -> {
                if(viewModel.character.value is DataState.Error){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Comic -> {
                if(viewModel.comices.value is DataState.Error){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Event -> {
                if(viewModel.event.value is DataState.Error){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Creator -> {
                if(viewModel.creator.value is DataState.Error){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
        }
    }
}
@BindingAdapter(value = ["app:showWhenSuccess"])
fun  showWhenSuccess(view: View,viewModel: SearchViewModel) {
    viewModel.searchFilterOption.observe(
        view.findFragment<SearchFragment>().viewLifecycleOwner
    ) { t ->
        when (t!!) {
            SearchFilter.Character -> {
                if(viewModel.character.value is DataState.Success){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Comic -> {
                if(viewModel.comices.value is DataState.Success){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Event -> {
                if(viewModel.event.value is DataState.Success){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Creator -> {
                if(viewModel.creator.value is DataState.Success){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
        }
    }
}
@BindingAdapter(value = ["app:showWhenLoading"])
fun showWhenLoading(view: View, viewModel: SearchViewModel) {
    viewModel.searchFilterOption.observe(
        view.findFragment<SearchFragment>().viewLifecycleOwner
    ) { t ->
        when (t!!) {
            SearchFilter.Character -> {
                if(viewModel.character.value is DataState.Loading){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Comic -> {
                if(viewModel.comices.value is DataState.Loading){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Event -> {
                if(viewModel.event.value is DataState.Loading){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
            SearchFilter.Creator -> {
                if(viewModel.creator.value is DataState.Loading){
                    view.visibility = View.VISIBLE
                }else{
                    view.visibility = View.GONE
                }
            }
        }
    }
}


