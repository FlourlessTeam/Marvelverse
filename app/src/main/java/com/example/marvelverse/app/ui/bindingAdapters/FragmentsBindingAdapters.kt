package com.example.marvelverse.app.ui.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story


@BindingAdapter( value=["app:items"])
fun setRecyclerStoriesItems(view:RecyclerView, items:MutableList<Story>?){
    val adapter= MoreStoriesAdapter()
         view.adapter= adapter
         adapter.submitList(items)
}

@BindingAdapter( value=["app:items"])
fun setRecyclerCreatorsItems(view:RecyclerView, items:MutableList<Creator>?){
    val adapter= MoreCreatorsAdapter()
    view.adapter= adapter
    adapter.submitList(items)
}