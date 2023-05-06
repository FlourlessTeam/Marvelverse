package com.example.marvelverse.app.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}