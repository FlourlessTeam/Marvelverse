package com.example.marvelverse.app.ui.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.databinding.ItemCardAboutBinding

class AboutAdapter(var histories: List<String>) :
    RecyclerView.Adapter<AboutAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCardAboutBinding.inflate(layoutInflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = histories.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(histories[position])


    class HistoryViewHolder(private val binding: ItemCardAboutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: String) {
            binding.aboutText.text = history
        }
    }
}