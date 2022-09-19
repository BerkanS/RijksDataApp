package com.berkan.rijksdataapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.berkan.rijksdataapp.domain.model.ArtObject

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM art_object")
    fun getArtObjects(): LiveData<List<ArtObject>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtObject(artObject: ArtObject)

    @Delete
    suspend fun deleteArtObject(artObject: ArtObject)
}