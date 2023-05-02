package com.example.marvelverse

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.app.ui.series.SeriesAdapter
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

@BindingAdapter(value = ["thumbnail"], requireAll = true)
fun ImageView.bindImageUrl(thumbnail: Thumbnail?) {
    thumbnail?.let {
        val imageUrlSecure = thumbnail.path.replace("http", "https")
        val validUrl = "$imageUrlSecure.${thumbnail.extension}"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}


@BindingAdapter("bindCharactersList")
fun RecyclerView.bindCharactersList(characters: List<Character>?) {
    (adapter as CharactersAdapter).submitList(characters ?: emptyList())
}

@BindingAdapter("bindSeriesList")
fun RecyclerView.bindSeriesList(series: List<Series>?) {
    (adapter as SeriesAdapter).submitList(series ?: emptyList())
}

@BindingAdapter("showIfLoading")
fun <T> ProgressBar.showIfLoading(dataState: DataState<T>?) {
    dataState?.let {
        visibility = if (dataState is DataState.Loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("showIfError")
fun <T> ImageView.showIfError(dataState: DataState<T>?) {
    dataState?.let {
        visibility = if (dataState is DataState.Error) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}