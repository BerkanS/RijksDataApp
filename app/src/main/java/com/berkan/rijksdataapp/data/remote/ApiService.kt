package com.berkan.rijksdataapp.data.remote

import com.berkan.rijksdataapp.domain.model.CollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("collection")
    suspend fun getObjectsByQuery(@Query("q") query: String, @Query("p") page: Int): Response<CollectionResponse>
}