package com.berkan.rijksdataapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.berkan.rijksdataapp.domain.model.ArtObject

class MainViewModel : ViewModel() {
    val pagingData: LiveData<PagingData<ArtObject>>
        get() = _pagingData
    private val _pagingData = MutableLiveData<PagingData<ArtObject>>()

    val selectedArtObject: LiveData<ArtObject?>
        get() = _selectedArtObject
    private val _selectedArtObject = MutableLiveData<ArtObject?>()

    fun setPagingData(pagingData: PagingData<ArtObject>) {
        _pagingData.postValue(pagingData)
    }

    fun setSelectedArtObject(artObject: ArtObject) {
        _selectedArtObject.postValue(artObject)
    }

}