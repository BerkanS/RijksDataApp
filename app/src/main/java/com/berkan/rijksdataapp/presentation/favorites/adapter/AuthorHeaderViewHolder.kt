package com.berkan.rijksdataapp.presentation.favorites.adapter

import androidx.recyclerview.widget.RecyclerView
import com.berkan.rijksdataapp.databinding.ItemAuthorHeaderBinding
import com.berkan.rijksdataapp.domain.model.AuthorHeader

class AuthorHeaderViewHolder(
    private val binding: ItemAuthorHeaderBinding,
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: AuthorHeader) {
        item?.let {
            binding.header = item
        }
    }
}