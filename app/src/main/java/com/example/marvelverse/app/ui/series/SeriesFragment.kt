package com.example.marvelverse.app.ui.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentSeriesBinding

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {
    private val seriesViewModel : SeriesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(seriesViewModel)
        binding.rvSeries.adapter = adapter
        binding.viewModel = seriesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}