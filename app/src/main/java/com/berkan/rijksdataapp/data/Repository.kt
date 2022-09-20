package com.berkan.rijksdataapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.berkan.rijksdataapp.data.remote.ApiService

const val NETWORK_PAGE_SIZE = 20

class Repository(
    private val remoteDataSource: ApiService
) {
    fun getObjects(query: String) =
        Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArtPagingSource(query, remoteDataSource)
            }
        ).liveData
}