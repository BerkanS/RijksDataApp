package com.berkan.rijksdataapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.berkan.rijksdataapp.data.remote.ApiService
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.domain.model.CODE_UNAUTHORIZED
import com.berkan.rijksdataapp.domain.model.LocalError
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArtPagingSource(
    private val query: String,
    private val remoteDataSource: ApiService
) : PagingSource<Int, ArtObject>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObject> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = remoteDataSource.getObjectsByQuery(
                query,
                page = pageIndex
            )
            if (response.code() == CODE_UNAUTHORIZED) throw LocalError.BadApiKey

            val results = response.body()?.artObjects ?: throw LocalError.EmptySearch
            if (results.isEmpty()) { throw LocalError.EmptySearch }

            val nextKey =
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)

            LoadResult.Page(
                data = results,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: LocalError.EmptySearch) {
            return LoadResult.Error(e)
        } catch (e: LocalError.BadApiKey) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}