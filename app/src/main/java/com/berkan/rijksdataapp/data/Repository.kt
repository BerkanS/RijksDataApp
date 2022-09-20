package com.berkan.rijksdataapp.data

import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.berkan.rijksdataapp.data.local.FavoritesDao
import com.berkan.rijksdataapp.data.remote.ApiService
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.domain.model.AuthorHeader
import com.berkan.rijksdataapp.domain.model.FavoriteObject

const val NETWORK_PAGE_SIZE = 20

class Repository(
    private val remoteDataSource: ApiService,
    private val localDataSource: FavoritesDao
) {
    fun getObjectsFromLocal() = localDataSource.getArtObjects()
        .switchMap { artObjects ->
            liveData {
                val resultList = mutableListOf<FavoriteObject>()
                var currentAuthor = "NONE"

                for (artObject in artObjects) {
                    if (artObject.author != currentAuthor) {
                        resultList.add(AuthorHeader(artObject.author))
                        currentAuthor = artObject.author
                    }
                    resultList.add(artObject)
                }
                emit(resultList)
            }
        }

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