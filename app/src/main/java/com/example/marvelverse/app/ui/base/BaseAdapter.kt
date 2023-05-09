package com.example.marvelverse.app.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.BR


abstract class BaseAdapter<T>(
    private val listener: BaseInteractionListener?,
) : ListAdapter<T, BaseAdapter.BaseViewHolder>(BaseDiffUtil<T>()) {

    abstract val layoutID: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutID, parent, false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder) bind(holder, position)
    }

    open fun bind(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            setVariable(BR.item, getItem(position))
            setVariable(BR.listener, listener)
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)


    open fun setItems(newItems: List<T>) {
        submitList(newItems)
    }

}

