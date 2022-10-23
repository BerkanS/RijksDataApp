package com.berkan.rijksdataapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkan.rijksdataapp.databinding.FragmentSearchBinding
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.presentation.MainViewModel
import com.berkan.rijksdataapp.presentation.search.adapter.SearchAdapter
import com.berkan.rijksdataapp.util.extensions.hideKeyboard
import com.berkan.rijksdataapp.util.extensions.onSearch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapter.ArtObjectClickListener {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

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
            when (val state = it.refresh) {
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    viewModel.setHasError(true)
                    binding.apply {
                        labelError.text = state.error.message
                        progressBar.visibility = View.GONE
                    }
                }
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
            if (it.append == LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setListeners() {
        binding.inputSearch.onSearch {
            val query = binding.inputSearch.text.toString()

            viewModel.getArtObjects(query).observe(viewLifecycleOwner) {
                it?.let { pagingData ->
                    mainViewModel.setPagingData(pagingData)
                    binding.listResults.scrollToPosition(0)
                }
            }
            context?.hideKeyboard(this.view)
        }
    }

    private fun setObservers() {
        mainViewModel.pagingData.observe(viewLifecycleOwner) {
            it?.let { pagingData ->
                searchAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }

        viewModel.hasError.observe(viewLifecycleOwner) {
            binding.hasError = it
            if (it) {
                searchAdapter.submitData(viewLifecycleOwner.lifecycle, PagingData.empty())
            }
        }
    }

    override fun onItemClick(artObject: ArtObject) {
        mainViewModel.setSelectedArtObject(artObject)
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment())
    }

}