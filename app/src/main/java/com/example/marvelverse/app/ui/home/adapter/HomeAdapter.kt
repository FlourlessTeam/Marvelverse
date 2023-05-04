package com.example.marvelverse.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.app.ui.home.ParentInteractionListener
import com.example.marvelverse.databinding.ListItemsCharacterBinding
import com.example.marvelverse.databinding.ListItemsComicBinding
import com.example.marvelverse.databinding.ListItemsEventBinding
import com.example.marvelverse.databinding.ListItemsSeriesBinding
import com.example.marvelverse.databinding.ListItemsStoriesBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener
import com.example.nestedrecyclerview.ui.base.BaseNestedRecyclerAdapter

class HomeAdapter(private val listener: ParentInteractionListener) : BaseNestedRecyclerAdapter<HomeItem>() {
    override fun sortItem(item: HomeItem) = item.rank

    override fun getTypeView(item: HomeItem): Int {
        return when (item) {
            is HomeItem.EventsItem -> TYPE_EVENT
            is HomeItem.CharactersItem -> TYPE_CHARACTER
            is HomeItem.ComicsItem -> TYPE_COMIC
            is HomeItem.SeriesItem -> TYPE_SERIES
            is HomeItem.StoriesItem -> TYPE_STORIES
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
                EventsHolder(binding,listener)
            }

            TYPE_CHARACTER -> {
                val binding = DataBindingUtil.inflate<ListItemsCharacterBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                CharactersHolder(binding, listener)

            }

            TYPE_COMIC -> {
                val binding = DataBindingUtil.inflate<ListItemsComicBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                ComicsHolder(binding,listener)
            }

            TYPE_SERIES -> {
                val binding = DataBindingUtil.inflate<ListItemsSeriesBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                SeriesHolder(binding,listener)
            }

            TYPE_STORIES -> {
                val binding = DataBindingUtil.inflate<ListItemsStoriesBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                StoriesHolder(binding,listener)
            }

            else -> {
                return null!!
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

class EventsHolder(binding: ListItemsEventBinding,private val interactionListener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsEventBinding) {
            binding.adapterRecycler=EventAdapter(item as List<Event>,interactionListener)
            binding.listener=interactionListener as ParentInteractionListener
            executePendingBindings()
        }
    }
}

class CharactersHolder(binding: ListItemsCharacterBinding, private val interactionListener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsCharacterBinding) {
            binding.adapterRecycler=CharactersAdapter(item as List<Character>,interactionListener)
            binding.listener=interactionListener as ParentInteractionListener
            executePendingBindings()
        }
    }
}

class ComicsHolder(binding: ListItemsComicBinding,private val interactionListener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsComicBinding) {
            binding.adapterRecycler=ComicsAdapter(item as List<Comic>,interactionListener)
            binding.listener=interactionListener as ParentInteractionListener
            executePendingBindings()
        }
    }
}

class SeriesHolder(binding: ListItemsSeriesBinding,private val interactionListener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsSeriesBinding) {
            binding.adapterRecycler=SeriesAdapter(item as List<Series>,interactionListener)
            binding.listener=interactionListener as ParentInteractionListener
            executePendingBindings()
        }
    }
}

class StoriesHolder(binding: ListItemsStoriesBinding,private val interactionListener:BaseInteractionListener ) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder(binding) {
    override fun bind(item: Any?) {
        with(binding as ListItemsStoriesBinding) {
            binding.adapterRecycler=StoriesAdapter(item as List<Story>,interactionListener)
            binding.listener=interactionListener as ParentInteractionListener
            executePendingBindings()
        }
    }
}

