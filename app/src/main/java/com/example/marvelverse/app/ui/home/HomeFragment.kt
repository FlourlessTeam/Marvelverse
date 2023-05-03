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
import com.example.marvelverse.app.ui.home.adapter.OnCharacterClickListener
import com.example.marvelverse.app.ui.home.adapter.OnComicClickListener
import com.example.marvelverse.app.ui.home.adapter.OnEventClickListener
import com.example.marvelverse.app.ui.home.adapter.OnSeriesClickListener
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Character
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    BaseInteractionListener {
    val viewModel:MainViewModel by viewModels()
    lateinit var adapter: HomeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= HomeAdapter(this)
        binding.recyclerView.adapter=adapter
        binding.lifecycleOwner=this
        val nestedItem= mutableListOf<HomeItem>()
        val characterItem= mutableListOf<Character>()

        viewModel.characters.observe(viewLifecycleOwner) {
            characterItem.addAll(it)

        }
        Log.d("cddddd", "onViewCreated: ${characterItem.size}")
        (binding.recyclerView.adapter as HomeAdapter).apply {
            nestedItem.add(HomeItem.CharactersItem(characterItem))
            nestedItem.add(HomeItem.CharactersItem(characterItem))
            nestedItem.add(HomeItem.CharactersItem(characterItem))
            nestedItem.add(HomeItem.CharactersItem(characterItem))
            this.addNestedItem(nestedItem)
        }
    }


//    override fun onCharacterClick(character: Character) {
//        Toast.makeText(requireContext(),character.name,Toast.LENGTH_SHORT).show()
//    }


}