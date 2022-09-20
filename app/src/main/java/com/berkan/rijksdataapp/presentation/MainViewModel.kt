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

    fun setPagingData(pagingData: PagingData<ArtObject>) {
        _pagingData.postValue(pagingData)
    }
}