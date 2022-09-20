package com.berkan.rijksdataapp.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berkan.rijksdataapp.databinding.ItemArtObjectBinding
import com.berkan.rijksdataapp.databinding.ItemAuthorHeaderBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.domain.model.AuthorHeader
import com.berkan.rijksdataapp.domain.model.FavoriteObject
import com.berkan.rijksdataapp.domain.model.Type
import com.berkan.rijksdataapp.presentation.search.adapter.ArtObjectViewHolder
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter

class FavoritesAdapter(
    private val clickListener: SearchAdapter.ArtObjectClickListener
) : ListAdapter<FavoriteObject, RecyclerView.ViewHolder>(CheckDiffCallback()) {

    override fun getItemViewType(position: Int) =
        getItem(position).type.ordinal


    override fun onCreateViewHolder(parent: ViewGroup, ordinalNumber: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewType = Type.first(ordinalNumber)

        return when (viewType) {
            Type.HEADER -> {
                AuthorHeaderViewHolder(
                    ItemAuthorHeaderBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
            Type.ART_OBJECT -> {
                ArtObjectViewHolder(
                    ItemArtObjectBinding.inflate(
                        inflater,
                        parent,
                        false
                    ), clickListener
                )
            }
            else -> {
                throw IllegalArgumentException("Adapter does not accept view of view type $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (item.type) {
            Type.HEADER -> {
                (holder as AuthorHeaderViewHolder).bind(item as AuthorHeader)
            }
            Type.ART_OBJECT -> {
                (holder as ArtObjectViewHolder).bind(item as ArtObject)
            }
        }
    }
}

private class CheckDiffCallback : DiffUtil.ItemCallback<FavoriteObject>() {
    override fun areItemsTheSame(oldItem: FavoriteObject, newItem: FavoriteObject) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FavoriteObject, newItem: FavoriteObject) =
        oldItem == newItem
}