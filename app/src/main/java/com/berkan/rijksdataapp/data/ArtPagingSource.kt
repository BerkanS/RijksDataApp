package com.berkan.rijksdataapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.berkan.rijksdataapp.data.remote.ApiService
import com.berkan.rijksdataapp.domain.model.ArtObject
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArtPagingSource(
    private val query: String,
    private val remoteDataSource: ApiService
) : PagingSource<Int, ArtObject>(){
    override fun getRefreshKey(state: PagingState<Int, ArtObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObject> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = remoteDataSource.getObjectsByQuery(
                query,
                page = pageIndex
            )
            val results = response.body()?.artObjects ?: emptyList()

            val nextKey =
                if (results.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = results,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}