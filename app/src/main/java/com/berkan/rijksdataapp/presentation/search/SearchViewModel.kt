package com.berkan.rijksdataapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.berkan.rijksdataapp.data.Repository
import com.berkan.rijksdataapp.domain.model.ArtObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val hasError: LiveData<Boolean>
        get() = _hasError
    private val _hasError = MutableLiveData(false)

    fun getArtObjects(query: String): LiveData<PagingData<ArtObject>> {
        _hasError.postValue(false)
        return repository.getObjectsFromRemote(query).cachedIn(viewModelScope)
    }

    fun setHasError(hasError: Boolean) {
        _hasError.postValue(hasError)
    }
}
