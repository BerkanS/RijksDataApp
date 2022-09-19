package com.berkan.rijksdataapp.presentation.favorites.adapter

import androidx.recyclerview.widget.RecyclerView
import com.berkan.rijksdataapp.databinding.ItemArtObjectBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter

class FavoritesItemViewHolder(
    private val binding: ItemArtObjectBinding,
    private val clickListener: SearchAdapter.ArtObjectClickListener
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: ArtObject?) {
        item?.let {
            binding.model = item
            binding.root.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }
}