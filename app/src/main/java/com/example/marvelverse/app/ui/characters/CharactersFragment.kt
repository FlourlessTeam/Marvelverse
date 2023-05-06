package com.example.marvelverse.app.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentCharactersBinding
import com.example.marvelverse.domain.entities.main.Character

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {
    private val charactersViewModel: CharactersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharactersAdapter(charactersViewModel)
        binding.rvCharacters.adapter = adapter
        binding.viewModel = charactersViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        charactersViewModel.characterEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }
    }
    private fun handleEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.ClickCharacterEvent ->navigateToDetails(event.character)
            CharactersEvent.BackToHome -> BackToHome()
        }
    }
    private fun navigateToDetails(character: Character) {
        Log.d("CharactersFragment", "ClickCharacterEvent $character")
    }
    fun BackToHome() {
        Log.d("CharactersFragment", "BackToHome")
    }

}