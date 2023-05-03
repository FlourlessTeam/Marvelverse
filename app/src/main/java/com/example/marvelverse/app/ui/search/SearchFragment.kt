package com.example.marvelverse.app.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.stories.MoreStoriesViewModel
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.databinding.FragmentSearchBinding

enum class SearchFilter{
    Character,
    Comic,
    Event,
    Creator
}

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) , BottomSheetListener {

    private val viewModel: SearchViewModel by viewModels()
    private var searchFilter:SearchFilter = SearchFilter.Character

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        searchViewListener(searchFilter)
        recyclerAdapterSetup(searchFilter)
    }

    private fun searchViewListener (searchFilter:SearchFilter){
        binding.searchViewResult.setOnQueryTextListener( object :OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when(searchFilter){
                    SearchFilter.Character -> viewModel.characterSearch(null , newText)
                    SearchFilter.Comic -> viewModel.comicSearch(null , newText)
                    SearchFilter.Event -> viewModel.eventSearch(null , newText)
                    SearchFilter.Creator -> viewModel.creatorSearch(null , newText)
                }
                return true
            }
        })
    }
    private fun recyclerAdapterSetup(searchFilter:SearchFilter){
        when(searchFilter){
            SearchFilter.Character -> TODO()
            SearchFilter.Comic -> TODO()
            SearchFilter.Event -> TODO()
            SearchFilter.Creator -> TODO()
        }
    }

    override fun onButtonClicked(searchFilter: SearchFilter) {
        this.searchFilter = searchFilter
        searchViewListener(searchFilter)
        recyclerAdapterSetup(searchFilter)
    }

}