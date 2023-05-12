package com.example.marvelverse.app.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.databinding.FragmentCharactersBinding
import com.example.marvelverse.domain.entities.main.Character

class CharactersFragment :
    InnerFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {
    private val viewModel: CharactersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharactersAdapter(viewModel)
        binding.rvCharacters.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.characterEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)

            }
        }
    }

    private fun handleEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.ClickCharacterEvent -> navigateToDetails(event.character)
            CharactersEvent.BackToHome -> BackToHome()
            else -> {}
        }
        viewModel.clearEvents()
    }

    private fun navigateToDetails(character: Character) {
        val directions =
            CharactersFragmentDirections.actionCharactersFragmentToDetailsCharacterFragment(
                character
            )
        findNavController().navigate(directions)
    }


    fun BackToHome() {
        Log.d("CharactersFragment", "BackToHome")
    }

}