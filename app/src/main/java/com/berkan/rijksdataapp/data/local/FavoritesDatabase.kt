package com.berkan.rijksdataapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.util.ObjectImageTypeConverter

@Database(
    entities = [
        ArtObject::class
    ], version = 1
)
@TypeConverters(
    ObjectImageTypeConverter::class
)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}