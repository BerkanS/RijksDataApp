package com.berkan.rijksdataapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.berkan.rijksdataapp.data.Repository
import com.berkan.rijksdataapp.domain.model.ArtObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getArtObjects(query: String) =
        repository.getObjectsFromRemote(query).cachedIn(viewModelScope)

    fun favoriteArtObject(artObject: ArtObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.favoriteArtObject(artObject)
        }
    }
}
