package com.example.marvelverse.app.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.app.ui.comics.ComicAdapter
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.events.EventsAdapter
import com.example.marvelverse.app.ui.stories.MoreStoriesAdapter
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


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

@BindingAdapter( value=["app:ImageUrl"])
fun setImageFromUrl(view:ImageView, url:Thumbnail?) {
    url?.let {
        val validUrl = "$url.path.${url.extension}"
        Glide.with(view)
            .load(validUrl)
            .into(view)
    }
}
@BindingAdapter( value=["app:items"])
fun setRecyclerComicsItems(view:RecyclerView, items:MutableList<Comic>?){
    val adapter= ComicAdapter()
    view.adapter= adapter
    adapter.submitList(items)
}

@BindingAdapter( value=["app:items"])
fun setRecyclerEventItems(view:RecyclerView, items:MutableList<Event>?){
    val adapter= EventsAdapter()
    view.adapter= adapter
    adapter.submitList(items)
}