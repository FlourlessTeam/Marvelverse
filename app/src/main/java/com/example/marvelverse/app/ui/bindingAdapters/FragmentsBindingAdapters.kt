package com.example.marvelverse.app.ui.bindingAdapters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


@BindingAdapter( value=["app:items"])
fun setRecyclerStoriesItems(view:RecyclerView, items:MutableList<Story>?){
    if(items!=null){
        (view.adapter as MoreStoriesAdapter).submitList(items)
    }
    else{
        (view.adapter as MoreStoriesAdapter).submitList(emptyList())
    }
}

@BindingAdapter( value=["app:items"])
fun setRecyclerCreatorsItems(view:RecyclerView, items:MutableList<Creator>?){
    if(items!=null){
        (view.adapter as MoreCreatorsAdapter).submitList(items)
    }
    else{
        (view.adapter as MoreCreatorsAdapter).submitList(emptyList())
    }
}