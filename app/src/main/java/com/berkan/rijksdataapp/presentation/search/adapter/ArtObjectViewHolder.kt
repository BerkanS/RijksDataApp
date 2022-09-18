package com.berkan.rijksdataapp.presentation.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.berkan.rijksdataapp.databinding.ItemArtObjectBinding
import com.berkan.rijksdataapp.domain.model.ArtObject

class ArtObjectViewHolder(
    private val binding: ItemArtObjectBinding,
    private val clickListener: SearchAdapter.ArtObjectClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ArtObject) {
        binding.model = item
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }
}