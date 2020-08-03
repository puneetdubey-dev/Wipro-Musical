package com.puneet.musicalwipro.albumsearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearch
import com.puneet.musicalwipro.albumsearch.viewmodels.SearchViewModel
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchViewModelTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockAlbumSearch: AlbumSearch

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModel(mockAlbumSearch)
    }

    @Test
    fun blankSearchTextIsNotSubmitted() = runBlocking {
        val searchTextBlank = " "
        searchViewModel.search(searchTextBlank)
        // Assert
        assertTrue(
            Mockito.verify(
                mockAlbumSearch,
                Mockito.times(0)
            ).searchAlbum(searchTextBlank) == null
        )
    }

    @Test
    fun emptySearchTextIsNotSubmitted() = runBlocking {
        val searchTextEmpty = ""
        searchViewModel.search(searchTextEmpty)
        // Assert
        assertTrue(
            Mockito.verify(
                mockAlbumSearch,
                Mockito.times(0)
            ).searchAlbum(searchTextEmpty) == null
        )
    }

    @Test
    fun serviceManagerIsCalledFromViewModel() = runBlocking {
        val searchText = "a non-null search text"
        searchViewModel.search(searchText)
        coroutineScope {
            // Assert
            Mockito.verify(mockAlbumSearch, Mockito.times(1)).searchAlbum(searchText)
        }
        assertTrue(true)
    }
}
