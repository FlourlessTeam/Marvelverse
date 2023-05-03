package com.example.marvelverse.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.app.ui.home.adapter.HomeAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    ParentInteractionListener {
    val viewModel:MainViewModel by viewModels()
    lateinit var adapter: HomeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= HomeAdapter(this)
        binding.recyclerView.adapter=adapter
        binding.lifecycleOwner=this
        val nestedItem= mutableListOf<HomeItem>()
//        val characterItem= mutableListOf<Character>()
        val eventItem= mutableListOf<Event>()

//        viewModel.characters.observe(viewLifecycleOwner) {
//            characterItem.addAll(it)
//            (binding.recyclerView.adapter as HomeAdapter).apply {
//                nestedItem.add(HomeItem.CharactersItem(characterItem))
//                nestedItem.add(HomeItem.CharactersItem(characterItem))
//                nestedItem.add(HomeItem.CharactersItem(characterItem))
//                nestedItem.add(HomeItem.CharactersItem(characterItem))
//                this.addNestedItem(nestedItem)
//            }
//
//        }
//        viewModel.events.observe(viewLifecycleOwner) {
//            eventItem.addAll(it)
//            Log.d("cddddd", "ddd: ${eventItem.size}")
//            (binding.recyclerView.adapter as HomeAdapter).apply {
//                nestedItem.add(HomeItem.EventsItem(eventItem))
//                nestedItem.add(HomeItem.EventsItem(eventItem))
//                nestedItem.add(HomeItem.EventsItem(eventItem))
//                nestedItem.add(HomeItem.EventsItem(eventItem))
//                this.addNestedItem(nestedItem)
//            }
//        }
        viewModel.homeItems.observe(viewLifecycleOwner){
            Log.d("cddddd", "ddd: ${it.size}")
            adapter.addNestedItem(it as MutableList<HomeItem>)
        }

//        Log.d("cddddd", "onViewCreated: ${characterItem.size}")

    }

    override fun onCharacterClick(character: Character) {
        Toast.makeText(requireContext(),character.name,Toast.LENGTH_SHORT).show()
    }

    override fun onEventClick(event: Event) {
        TODO("Not yet implemented")
    }

    override fun onComicClick(comic: Comic) {
        TODO("Not yet implemented")
    }

    override fun onStoriesClick(stories: Story) {
        TODO("Not yet implemented")
    }

    override fun onSeriesClick(series: Series) {
        TODO("Not yet implemented")
    }

    override fun onViewAllCharactersClick() {
        TODO("Not yet implemented")
    }

    override fun onViewAllEventsClick() {
        TODO("Not yet implemented")
    }

    override fun onViewAllComicsClick() {
        TODO("Not yet implemented")
    }

    override fun onViewAllStoriesClick() {
        TODO("Not yet implemented")
    }

    override fun onViewAllSeriesClick() {
        TODO("Not yet implemented")
    }


//    override fun onCharacterClick(character: Character) {
//        Toast.makeText(requireContext(),character.name,Toast.LENGTH_SHORT).show()
//    }


}