package com.berkan.rijksdataapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.berkan.rijksdataapp.data.local.FavoritesDao
import com.berkan.rijksdataapp.data.local.FavoritesDatabase
import com.berkan.rijksdataapp.data.remote.ApiService
import com.berkan.rijksdataapp.domain.model.ArtObject
import com.berkan.rijksdataapp.domain.model.AuthorHeader
import com.berkan.rijksdataapp.domain.model.Fakes
import com.berkan.rijksdataapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FavoritesDatabase
    private lateinit var repository: Repository
    private lateinit var dao: FavoritesDao

    private val apiService = mockk<ApiService>(relaxed = true)

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.favoritesDao()
        repository = Repository(apiService, dao)
    }

    @Test
    fun testGetObjectsFromLocalIsGroupedByAuthor() = runTest {
        val artObjects = listOf(
            Fakes.artObject,
            Fakes.artObjectTwo,
            Fakes.artObjectThree,
            Fakes.artObjectFour,
            Fakes.artObjectFive,
            Fakes.artObjectSix
        )

        artObjects.shuffled().forEach {
            dao.insertArtObject(it)
        }

        val actualObjects = repository.getObjectsFromLocal().getOrAwaitValue()

        // Looking at the SQL query, it should return ordered by author. And looking at the fakes
        // it should be grouped in the following order
        val isGroupedCorrectly =
            actualObjects[0] == AuthorHeader("Rembrandt") &&
                    (actualObjects[1] as ArtObject).author == "Rembrandt" &&
                    (actualObjects[2] as ArtObject).author == "Rembrandt" &&
                    (actualObjects[3] as ArtObject).author == "Rembrandt" &&
                    actualObjects[4] == AuthorHeader("Van Gogh") &&
                    (actualObjects[5] as ArtObject).author == "Van Gogh" &&
                    actualObjects[6] == AuthorHeader("Vermeer") &&
                    (actualObjects[7] as ArtObject).author == "Vermeer" &&
                    (actualObjects[8] as ArtObject).author == "Vermeer"

        assertThat(isGroupedCorrectly).isTrue()
    }

    @Test
    fun testExistsLocallyTrue() = runTest {
        val artObject = Fakes.artObject
        val artObjectTwo = Fakes.artObjectTwo

        dao.insertArtObject(artObject)
        dao.insertArtObject(artObjectTwo)

        assertThat(repository.existsLocally(artObject)).isTrue()
    }

    @Test
    fun testExistsLocallyFalse() = runTest {
        val artObject = Fakes.artObject
        val artObjectTwo = Fakes.artObjectTwo

        dao.insertArtObject(artObject)

        assertThat(repository.existsLocally(artObjectTwo)).isFalse()
    }



    @After
    fun cleanup() {
        database.close()
    }
}