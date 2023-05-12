package com.example.marvelverse.app.ui.series

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.databinding.FragmentSeriesBinding
import com.example.marvelverse.domain.entities.main.Series

class SeriesFragment : InnerFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {
    private val viewModel: SeriesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(viewModel)
        binding.rvSeries.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.seriesEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }
    }

    fun handleEvent(event: SeriesEvent) {
        when (event) {
            is SeriesEvent.ClickSeriesEvent -> navigateToDetails(event.series)
            SeriesEvent.BackToHome -> BackToHome()
            else -> {}
        }
        viewModel.clearEvents()

    }

    fun navigateToDetails(series: Series) {
        val direction = SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(series)
        findNavController().navigate(direction)
    }

    fun BackToHome() {
        Log.d("SeriesFragment", "BackToHome")
    }

}