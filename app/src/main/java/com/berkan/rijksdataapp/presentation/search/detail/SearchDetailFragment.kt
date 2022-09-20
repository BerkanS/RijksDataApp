package com.berkan.rijksdataapp.presentation.search.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.berkan.rijksdataapp.databinding.FragmentSearchDetailBinding
import com.berkan.rijksdataapp.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    private lateinit var binding: FragmentSearchDetailBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailBinding.inflate(inflater, container, false)

        setListeners()
        setObservers()
        return binding.root
    }

    private fun setListeners() {}

    private fun setObservers() {
        mainViewModel.selectedArtObject.observe(viewLifecycleOwner) {
            it?.let { artObject ->
                binding.model = artObject
            }
        }
    }


}