package com.berkan.rijksdataapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.berkan.rijksdataapp.domain.model.ArtObject

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM art_object ORDER BY author")
    fun getArtObjects(): LiveData<List<ArtObject>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtObject(artObject: ArtObject)

    @Query("SELECT EXISTS(SELECT * FROM art_object WHERE objectNumber = :objectNumber)")
    suspend fun isObjectExists(objectNumber: String): Boolean

    @Delete
    suspend fun deleteArtObject(artObject: ArtObject)
}