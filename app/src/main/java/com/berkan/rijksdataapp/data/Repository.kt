package com.berkan.rijksdataapp.data

import com.berkan.rijksdataapp.data.remote.ApiService

class Repository(
    private val remoteDataSource: ApiService
) {

    suspend fun getObjectsByQuery(query: String) =
        remoteDataSource.getObjectsByQuery(query)
}