package com.example.marvelverse.app.ui.comics

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentComicsBinding
import com.example.marvelverse.domain.entities.main.Comic


class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) {

    private val comicViewModel: ComicViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ComicAdapter(comicViewModel)
        binding.comicRecyclerView.adapter = adapter
        binding.viewModel = comicViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        comicViewModel.comicEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleComic(it)
            }
        }
    }

    fun handleComic(event: ComicEvent) {
        when (event) {
            is ComicEvent.ClickComicEvent -> navigateToDetails(event.comic)
            ComicEvent.BackToHome -> BackToHome()
        }
    }

    fun navigateToDetails(comic: Comic) {
        Log.d("ComicFragment", "ClickComicEvent $comic")
    }

    fun BackToHome() {
        Log.d("ComicFragment", "BackToHome")
    }

}