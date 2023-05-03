package com.example.marvelverse.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.databinding.ListItemsCharacterBinding
import com.example.marvelverse.databinding.ListItemsComicBinding
import com.example.marvelverse.databinding.ListItemsEventBinding
import com.example.marvelverse.databinding.ListItemsSeriesBinding
import com.example.marvelverse.databinding.ListItemsStoriesBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Event
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener
import com.example.nestedrecyclerview.ui.base.BaseNestedRecyclerAdapter

class HomeAdapter(private val listeneer:BaseInteractionListener) : BaseNestedRecyclerAdapter<HomeItem>() {
    override fun sortItem(item: HomeItem) = item.rank


    override fun getTypeView(item: HomeItem): Int {
        return when (item) {
            is HomeItem.EventsItem -> TYPE_EVENT
            is HomeItem.CharactersItem -> TYPE_CHARACTER
            is HomeItem.ComicsItem -> TYPE_COMIC
            is HomeItem.SeriesItem -> TYPE_SERIES
            is HomeItem.StoriesItem -> TYPE_STORIES
            else -> {
                throw IllegalArgumentException("Invalid type of data " + item)
            }
        }
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseNestedRecyclerViewHolder {
        return when (viewType) {
            TYPE_EVENT -> {
                val binding = DataBindingUtil.inflate<ListItemsEventBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                EventsHolder(binding)
            }

            TYPE_CHARACTER -> {
                val binding = DataBindingUtil.inflate<ListItemsCharacterBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                CharactersHolder(binding, listeneer)

            }

            TYPE_COMIC -> {
                val binding = DataBindingUtil.inflate<ListItemsComicBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                ComicsHolder(binding)
            }

            TYPE_SERIES -> {
                val binding = DataBindingUtil.inflate<ListItemsSeriesBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                SeriesHolder(binding)
            }

            else -> {
                val binding = DataBindingUtil.inflate<ListItemsStoriesBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                StoriesHolder(binding)
            }
        }
    }

    override fun bindItem(holder: BaseNestedRecyclerViewHolder, item: HomeItem) {
        return when (item) {
            is HomeItem.EventsItem -> {
                (holder as EventsHolder).bind(item.eventList)
            }

            is HomeItem.CharactersItem -> {
                (holder as CharactersHolder).bind(item.charactersList)
            }

            is HomeItem.ComicsItem -> {
                (holder as ComicsHolder).bind(item.comicsList)
            }

            is HomeItem.SeriesItem -> {
                (holder as SeriesHolder).bind(item.seriesList)
            }

            is HomeItem.StoriesItem -> {
                (holder as StoriesHolder).bind(item.storiesList)
            }

            else -> {}
        }
    }

    companion object {
        const val TYPE_EVENT = R.layout.list_items_event
        const val TYPE_CHARACTER = R.layout.list_items_character
        const val TYPE_COMIC = R.layout.list_items_comic
        const val TYPE_SERIES = R.layout.list_items_series
        const val TYPE_STORIES = R.layout.list_items_stories
    }

}

class EventsHolder(binding: ListItemsEventBinding) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsEventBinding) {

            executePendingBindings()
        }
    }
}

class CharactersHolder(binding: ListItemsCharacterBinding,val listener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsCharacterBinding) {
            binding.adapterRecycler=CharactersAdapter(item as List<Character>,listener)
            executePendingBindings()
        }
    }
}

class ComicsHolder(binding: ListItemsComicBinding) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsComicBinding) {

            executePendingBindings()
        }
    }
}

class SeriesHolder(binding: ListItemsSeriesBinding) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsSeriesBinding) {

            executePendingBindings()
        }
    }
}

class StoriesHolder(binding: ListItemsStoriesBinding) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsStoriesBinding) {

            executePendingBindings()
        }
    }
}

