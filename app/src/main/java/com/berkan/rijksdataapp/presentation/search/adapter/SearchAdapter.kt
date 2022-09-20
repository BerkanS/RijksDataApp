package com.berkan.rijksdataapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkan.rijksdataapp.databinding.ItemArtObjectBinding
import com.berkan.rijksdataapp.domain.model.ArtObject

class SearchAdapter(
    private val clickListener: ArtObjectClickListener
) : PagingDataAdapter<ArtObject, RecyclerView.ViewHolder>(CheckDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArtObjectViewHolder(
            ItemArtObjectBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickListener
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ArtObjectViewHolder).bind(getItem(position))

    interface ArtObjectClickListener {
        fun onItemClick(artObject: ArtObject)
    }
}

private class CheckDiffCallback : DiffUtil.ItemCallback<ArtObject>() {
    override fun areItemsTheSame(oldItem: ArtObject, newItem: ArtObject) =
        oldItem.objectNumber == newItem.objectNumber

    override fun areContentsTheSame(oldItem: ArtObject, newItem: ArtObject) =
        oldItem == newItem

}