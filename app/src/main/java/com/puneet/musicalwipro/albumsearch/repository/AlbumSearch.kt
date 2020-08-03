package com.puneet.musicalwipro.albumsearch.repository

import com.puneet.musicalwipro.albumsearch.model.AlbumSearchResult

interface AlbumSearch {
    suspend fun searchAlbum(searchText: String): AlbumSearchResult?
}