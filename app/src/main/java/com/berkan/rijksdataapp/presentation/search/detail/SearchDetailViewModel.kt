package com.berkan.rijksdataapp.presentation.search.detail

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
class SearchDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val objectFavorited: LiveData<Boolean>
        get() = _objectFavorited
    private val _objectFavorited = MutableLiveData(false)

    fun isObjectExists(artObject: ArtObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.existsLocally(artObject).let { exists ->
                _objectFavorited.postValue(exists)
            }
        }
    }

    fun favoriteObject(artObject: ArtObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.existsLocally(artObject).let { exists ->
                if (exists) {
                    repository.deleteArtObject(artObject)
                    _objectFavorited.postValue(false)
                } else {
                    repository.favoriteArtObject(artObject)
                    _objectFavorited.postValue(true)
                }
            }
        }
    }

}