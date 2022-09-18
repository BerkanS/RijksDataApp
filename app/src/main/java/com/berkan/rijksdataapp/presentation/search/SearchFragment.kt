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
    }

    private fun setListeners() {
        binding.inputSearch.onSearch {
            val query = binding.inputSearch.text.toString()
            viewModel.getArtObjects(query)
            binding.progressBar.visibility = View.VISIBLE
            context?.hideKeyboard(this.view)
        }
    }

    private fun setObservers() {
        viewModel.artObject.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onItemClick(artObject: ArtObject) {
        Toast.makeText(context, artObject.longTitle, Toast.LENGTH_SHORT).show()
    }

}