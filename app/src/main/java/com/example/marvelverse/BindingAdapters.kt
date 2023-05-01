package com.example.marvelverse

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.app.ui.characters.CharactersAdapter
import com.example.marvelverse.domain.entities.main.Character

@BindingAdapter(value = ["imageUrl", "imageExtension"], requireAll = true)
fun ImageView.bindImageUrl(imageUrl: String?, imageExtension: String?) {
    if (imageUrl != null && imageExtension != null) {
        val imageUrlSecure = imageUrl.replace("http","https")
        val validUrl = "$imageUrlSecure.$imageExtension"
        Glide.with(this)
            .load(validUrl)
            .into(this)
    }
}

@BindingAdapter("bindCharactersList")
fun RecyclerView.bindCharactersList(characters: List<Character>?) {
    (adapter as CharactersAdapter).submitList(characters ?: emptyList())
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