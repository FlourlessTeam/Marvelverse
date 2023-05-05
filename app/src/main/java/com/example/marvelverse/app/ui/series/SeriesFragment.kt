package com.example.marvelverse.app.ui.series

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentSeriesBinding
import com.example.marvelverse.domain.entities.main.Series

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {
    private val seriesViewModel: SeriesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(seriesViewModel)
        binding.rvSeries.adapter = adapter
        binding.viewModel = seriesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        seriesViewModel.seriesEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }
    }

    fun handleEvent(event: SeriesEvent) {
        when (event) {
            is SeriesEvent.ClickSeriesEvent -> navigateToDetails(event.series)
            SeriesEvent.BackToHome -> BackToHome()
        }
    }

    fun navigateToDetails(series: Series) {
        Log.d("SeriesFragment", "ClickSeriesEvent $series")
    }

    fun BackToHome() {
        Log.d("SeriesFragment", "BackToHome")
    }

}