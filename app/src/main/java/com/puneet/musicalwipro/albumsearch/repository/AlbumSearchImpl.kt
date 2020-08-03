package com.puneet.musicalwipro.albumsearch.repository

import android.content.Context
import com.puneet.musicalwipro.albumsearch.R
import com.puneet.musicalwipro.albumsearch.model.AlbumSearchResult
import com.puneet.musicalwipro.albumsearch.retrofit.SearchApiService
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

class AlbumSearchImpl @Inject constructor(
    private val searchApiService: SearchApiService,
    context: Context
) : AlbumSearch {

    private val apiVersion = context.getString(R.string.search_api_version)
    private val apiKey = context.getString(R.string.api_key)

    private var albumSearchCall: Call<AlbumSearchResult>? = null

    override suspend fun searchAlbum(searchText: String): AlbumSearchResult? {
        albumSearchCall?.cancel()
        albumSearchCall = searchApiService.searchAlbum(apiVersion, apiKey, searchText)
        return try {
            albumSearchCall?.execute()?.body()
        } catch (e: Exception) {
            null
        }
    }
}