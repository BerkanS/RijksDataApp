package com.berkan.rijksdataapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkan.rijksdataapp.data.Repository
import com.berkan.rijksdataapp.domain.model.ArtObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val artObjects = repository.getObjectsFromLocal()


}