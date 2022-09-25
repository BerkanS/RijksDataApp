package com.berkan.rijksdataapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.domain.model.Fakes
import com.berkan.rijksdataapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoritesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FavoritesDatabase
    private lateinit var dao: FavoritesDao
    private lateinit var artObject: ArtObject

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.favoritesDao()

        artObject = Fakes.artObject
    }

    @Test
    fun testInsertFavoriteObjectSuccess() = runTest {
        dao.insertArtObject(artObject)

        val allArtObjects = dao.getArtObjects().getOrAwaitValue()

        assertThat(allArtObjects).contains(artObject)
    }

    @Test
    fun testDeleteFavoriteObjectSuccess() = runTest {
        dao.insertArtObject(artObject)
        dao.deleteArtObject(artObject)

        val allArtObjects = dao.getArtObjects().getOrAwaitValue()

        assertThat(allArtObjects).doesNotContain(artObject)
    }

    @After
    fun cleanup() {
        database.close()
    }

}