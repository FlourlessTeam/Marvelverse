package com.example.marvelverse.app.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentCharactersBinding

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {
    private val charactersViewModel: CharactersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharactersAdapter(charactersViewModel)
        binding.rvCharacters.adapter = adapter
        binding.viewModel = charactersViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}