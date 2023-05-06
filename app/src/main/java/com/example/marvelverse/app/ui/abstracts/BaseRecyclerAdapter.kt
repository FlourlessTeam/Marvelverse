package com.example.marvelverse.app.ui.abstracts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<T, VB : ViewBinding>
    (
    utilCallBack: DiffUtil.ItemCallback<T>,
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val onViewClicked: (T) -> Unit
) :
    ListAdapter<T, BaseRecyclerAdapter.BaseViewHolder<T, VB>>(utilCallBack) {

    private fun getViewHolder(
        parent: ViewGroup,
        bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
    ): BaseViewHolder<T, VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)

        return object : BaseViewHolder<T, VB>(binding) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        return getViewHolder(parent, bindingInflater)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.bind(getItem(position), onViewClicked = onViewClicked)
        holder.binding.root
    }

    abstract class BaseViewHolder<T, VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

    abstract fun BaseViewHolder<T, VB>.bind(
        marvelData: T,
        onViewClicked: (T) -> Unit
    )

}


