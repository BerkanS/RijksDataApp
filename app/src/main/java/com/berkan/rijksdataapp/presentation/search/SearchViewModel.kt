package com.berkan.rijksdataapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val artObject: LiveData<List<ArtObject>>
        get() = _artObject
    private val _artObject = MutableLiveData<List<ArtObject>>()

    fun getArtObjects(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getObjectsByQuery(query)

            if (response.isSuccessful) {
                response.body()?.artObjects?.let {
                    _artObject.postValue(it)
                }
            }
        }
    }
}