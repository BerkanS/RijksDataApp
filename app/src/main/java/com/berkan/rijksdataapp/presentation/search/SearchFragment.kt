package com.berkan.rijksdataapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkan.rijksdataapp.databinding.FragmentSearchBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapter.ArtObjectClickListener {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setAdapter()
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter(this)
        binding.listResults.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setListeners() {
        viewModel.getArtObjects("")
    }

    private fun setObservers() {
        viewModel.artObject.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
    }

    override fun onItemClick(artObject: ArtObject) {
        Toast.makeText(context, artObject.longTitle, Toast.LENGTH_SHORT).show()
    }

}