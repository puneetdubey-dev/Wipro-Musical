package com.puneet.musicalwipro.albumsearch

import com.puneet.musicalwipro.albumsearch.extensions.toCommonSearchResult
import com.puneet.musicalwipro.albumsearch.model.Album
import com.puneet.musicalwipro.albumsearch.model.AlbumList
import com.puneet.musicalwipro.albumsearch.model.AlbumMatches
import com.puneet.musicalwipro.albumsearch.model.AlbumSearchResult
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchResultExtensionTests {

    @Before
    fun setupTest() {
    }

    @Test
    fun albumSearchResultExtension() {

        // Arrange
        val albumSearchResult =
            AlbumSearchResult(
                AlbumMatches(
                    AlbumList(
                        listOf(
                            Album(
                                "id",
                                "name",
                                "artist",
                                null,
                                null
                            )
                        )
                    )
                )
            )

        val commonSearchResult = albumSearchResult.toCommonSearchResult()

        assertTrue(commonSearchResult != null)
        assertTrue(commonSearchResult!!.isNotEmpty())
    }
}
