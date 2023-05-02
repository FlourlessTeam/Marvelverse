package com.example.marvelverse.app.ui.viewAll

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
@BindingAdapter( value=["app:items"])
fun setRecyclerStoriesItems(view:RecyclerView, items:Single<MutableList<Story>>?){
    items?.
    subscribeOn(Schedulers.io())?.
    observeOn(AndroidSchedulers.mainThread())?.
    subscribe({
        val adapter=ViewAllStoriesAdapter()
         view.adapter= adapter
        adapter.submitList(it)
    },{

    })
}

@SuppressLint("CheckResult")
@BindingAdapter( value=["app:items"])
fun setRecyclerCreatorsItems(view:RecyclerView, items:Single<MutableList<Creator>>?){
    items?.
    subscribeOn(Schedulers.io())?.
    observeOn(AndroidSchedulers.mainThread())?.
    subscribe({
        val adapter=ViewAllCreatorsAdapter()
        view.adapter= adapter
        adapter.submitList(it)
    },{

    })
}