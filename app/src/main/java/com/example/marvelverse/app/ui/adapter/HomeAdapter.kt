package com.example.marvelverse.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.app.ui.interfaces.ParentInteractionListener
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.databinding.ListItemsCharacterBinding
import com.example.marvelverse.databinding.ListItemsComicBinding
import com.example.marvelverse.databinding.ListItemsEventBinding
import com.example.marvelverse.databinding.ListItemsSeriesBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.app.ui.base.BaseNestedRecyclerAdapter



class HomeAdapter(private val listener: ParentInteractionListener) : BaseNestedRecyclerAdapter<HomeItem>() {
    override fun sortItem(item: HomeItem) = item.rank

    override fun getTypeView(item: HomeItem): Int {
        return when (item) {
            is HomeItem.EventsItem -> TYPE_EVENT
            is HomeItem.CharactersItem -> TYPE_CHARACTER
            is HomeItem.ComicsItem -> TYPE_COMIC
            is HomeItem.SeriesItem -> TYPE_SERIES
        }
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseNestedRecyclerViewHolder<*> {
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

          else-> {
                val binding = DataBindingUtil.inflate<ListItemsSeriesBinding>(
                    inflater,
                    viewType,
                    parent,
                    false
                )
                SeriesHolder(binding,listener)
            }
        }
    }

    override fun bindItem(holder: BaseNestedRecyclerViewHolder<HomeItem>, item: HomeItem) {
        return when (item) {
            is HomeItem.EventsItem -> {
                (holder as EventsHolder).bind(item.eventList,listener)
            }

            is HomeItem.CharactersItem -> {
                (holder as CharactersHolder).bind(item.charactersList,listener)
            }

            is HomeItem.ComicsItem -> {
                (holder as ComicsHolder).bind(item.comicsList,listener)
            }

            is HomeItem.SeriesItem -> {
                (holder as SeriesHolder).bind(item.seriesList,listener)
            }


        }
    }


    companion object {
        const val TYPE_EVENT = R.layout.list_items_event
        const val TYPE_CHARACTER = R.layout.list_items_character
        const val TYPE_COMIC = R.layout.list_items_comic
        const val TYPE_SERIES = R.layout.list_items_series
    }

}

class EventsHolder(binding: ListItemsEventBinding,private val interactionListener: BaseInteractionListener) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder<Event>(binding) {
    override fun getAdapter(listener: BaseInteractionListener): BaseAdapter<Event> {
        return EventAdapter(interactionListener)
    }
}

class CharactersHolder(binding: ListItemsCharacterBinding, private val interactionListener: BaseInteractionListener) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder<Character>(binding) {
    override fun getAdapter(listener: BaseInteractionListener): BaseAdapter<Character> {
        return CharactersAdapter(interactionListener)
    }

}

class ComicsHolder(binding: ListItemsComicBinding,private val interactionListener: BaseInteractionListener) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder<Comic>(binding) {
    override fun getAdapter(listener: BaseInteractionListener): BaseAdapter<Comic> {
        return ComicsAdapter(interactionListener)
    }

}

class SeriesHolder(binding: ListItemsSeriesBinding,private val interactionListener: BaseInteractionListener) :
    BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder<Series>(binding) {
    override fun getAdapter(listener: BaseInteractionListener): BaseAdapter<Series> {
        return SeriesAdapter(interactionListener)
    }
}


