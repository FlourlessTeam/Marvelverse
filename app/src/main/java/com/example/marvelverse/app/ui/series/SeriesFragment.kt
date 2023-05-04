package com.example.marvelverse.app.ui.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentSeriesBinding
import com.example.marvelverse.domain.entities.main.Series

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {
    private val seriesViewModel : SeriesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(
            object :SeriesAdapter.ClickListener{
                override fun onClick(series: Series) {
                    TODO("Not yet implemented")
                }
            }
        )
        binding.rvSeries.adapter = adapter
        binding.viewModel = seriesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}