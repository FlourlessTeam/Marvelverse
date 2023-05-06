package com.example.nestedrecyclerview.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.BR
import com.example.marvelverse.app.ui.home.base.BaseInteractionListener


abstract class BaseNestedRecyclerAdapter<T> :
    RecyclerView.Adapter<BaseNestedRecyclerAdapter.BaseNestedRecyclerViewHolder<T>>() {

    private var nestedItems = mutableListOf<T>()

    abstract fun sortItem(item: T): Int

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItems: MutableList<T>) {
        nestedItems = newItems
        nestedItems.apply {
            sortBy { sortItem(it) }
        }
        notifyDataSetChanged()
    }

    abstract fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseNestedRecyclerViewHolder<*>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseNestedRecyclerViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater, parent, viewType) as BaseNestedRecyclerViewHolder<T>
    }

    abstract fun bindItem(holder: BaseNestedRecyclerViewHolder<T>, item: T)

    override fun onBindViewHolder(holder: BaseNestedRecyclerViewHolder<T>, position: Int) {
        bindItem(holder, nestedItems[position])
    }

    override fun getItemCount() = nestedItems.size

    abstract fun getTypeView(item: T): Int

    override fun getItemViewType(position: Int): Int {
        return getTypeView(nestedItems[position])
    }

    abstract class BaseNestedRecyclerViewHolder<T>(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        abstract fun getAdapter(listener: BaseInteractionListener): BaseAdapter<T>

        open fun bind(item: List<T>, interactionListener: BaseInteractionListener) {
            with(binding) {
                val adapterRecycler = getAdapter(interactionListener)
                adapterRecycler.setItems(item)
                binding.setVariable(BR.adapterRecycler, adapterRecycler)
                binding.setVariable(BR.listener, interactionListener)
                executePendingBindings()
            }
        }
    }

}