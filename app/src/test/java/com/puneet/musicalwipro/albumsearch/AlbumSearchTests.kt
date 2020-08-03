package com.puneet.musicalwipro.albumsearch

import android.content.Context
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearch
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearchImpl
import com.puneet.musicalwipro.albumsearch.retrofit.SearchApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AlbumSearchTests {

    @Mock
    lateinit var mockSearchApiService: SearchApiService

    @Mock
    lateinit var context: Context

    private lateinit var albumSearch: AlbumSearch

    private val apiKey = "anAPIKey"
    private val apiVersion = "anAPIVersion"

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(context.getString(R.string.api_key)).thenReturn(apiKey)
        Mockito.`when`(context.getString(R.string.search_api_version)).thenReturn(apiVersion)

        albumSearch =
            AlbumSearchImpl(
                mockSearchApiService,
                context
            )
    }

    @Test
    fun searchingAlbumMakesApiCall() = runBlocking {

        val searchText = "test"
        albumSearch.searchAlbum(searchText)
        Mockito.verify(
            mockSearchApiService,
            Mockito.times(1)
        ).searchAlbum(apiVersion, apiKey, searchText)
        assert(true)
    }
}
