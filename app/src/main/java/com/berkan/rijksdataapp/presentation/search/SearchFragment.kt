package com.berkan.rijksdataapp.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkan.rijksdataapp.databinding.FragmentSearchBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter
import com.berkan.rijksdataapp.util.hideKeyboard
import com.berkan.rijksdataapp.util.onSearch
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

        searchAdapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading || it.append == LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setListeners() {
        binding.inputSearch.onSearch {
            val query = binding.inputSearch.text.toString()

            viewLifecycleOwner.lifecycle
            viewModel.getArtObjects(query).observe(viewLifecycleOwner) {
                it?.let { pagingData ->
                    searchAdapter.submitData(lifecycle, pagingData)
                    binding.listResults.scrollToPosition(0)
                }
            }
            context?.hideKeyboard(this.view)
        }
    }

    private fun setObservers() {

    }

    override fun onItemClick(artObject: ArtObject) {
        Toast.makeText(context, artObject.longTitle, Toast.LENGTH_SHORT).show()
        viewModel.favoriteArtObject(artObject)
    }

}