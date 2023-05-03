package com.example.marvelverse.app.ui.series

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentSeriesBinding

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seriesViewModel: ComicsViewModel = ViewModelProvider(this)[ComicsViewModel::class.java]
        binding.lifecycleOwner=this
        binding.viewModel=seriesViewModel
        var comicsAdapter:ComicsAdapter= ComicsAdapter()
//        comicsAdapter.submitList()

    }

}