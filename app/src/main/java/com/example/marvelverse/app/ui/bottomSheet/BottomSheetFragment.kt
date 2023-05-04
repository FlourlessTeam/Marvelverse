package com.example.marvelverse.app.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.search.SearchFilter
import com.example.marvelverse.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment(private val bottomSheetListener: BottomSheetListener) :
    BottomSheetDialogFragment() {


    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonShow.setOnClickListener {
            buttonClicked()
        }

    }
    private fun buttonClicked() {
        val selectedSearchFilter = when (binding.chipGroupSearchOption.checkedChipId) {
            binding.chipCharacter.id -> SearchFilter.Character
            binding.chipComics.id -> SearchFilter.Comic
            binding.chipCreator.id -> SearchFilter.Creator
            binding.chipEvent.id -> SearchFilter.Event
            else -> SearchFilter.Character
        }
        bottomSheetListener.onButtonClicked(selectedSearchFilter)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}