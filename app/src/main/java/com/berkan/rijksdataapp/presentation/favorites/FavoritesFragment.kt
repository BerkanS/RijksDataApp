package com.berkan.rijksdataapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkan.rijksdataapp.databinding.FragmentFavoritesBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.presentation.favorites.adapter.FavoritesAdapter
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), SearchAdapter.ArtObjectClickListener {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter = FavoritesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setAdapter()
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setAdapter() {
        binding.listFavorites.adapter = favoritesAdapter
        binding.listFavorites.layoutManager = LinearLayoutManager(context)
    }

    private fun setListeners() {}

    private fun setObservers() {
        viewModel.artObjects.observe(viewLifecycleOwner) {
            it?.let {
                favoritesAdapter.submitList(it)
            }
        }
    }

    override fun onItemClick(artObject: ArtObject) {

    }
}