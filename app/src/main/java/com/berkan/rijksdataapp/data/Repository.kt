package com.berkan.rijksdataapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.berkan.rijksdataapp.data.local.FavoritesDao
import com.berkan.rijksdataapp.data.remote.ApiService
import com.berkan.rijksdataapp.domain.model.ArtObject

const val NETWORK_PAGE_SIZE = 20

class Repository(
    private val remoteDataSource: ApiService,
    private val localDataSource: FavoritesDao
) {
    fun getObjectsFromLocal() = localDataSource.getArtObjects()

    fun getObjectsFromRemote(query: String) =
        Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArtPagingSource(query, remoteDataSource)
            }
        ).liveData

    suspend fun favoriteArtObject(artObject: ArtObject) =
        localDataSource.insertArtObject(artObject)

}