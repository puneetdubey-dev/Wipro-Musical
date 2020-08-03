package com.puneet.musicalwipro.albumsearch.retrofit

import com.puneet.musicalwipro.albumsearch.model.AlbumSearchResult
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApiService {

    @POST("/{api_version}/?method=album.search&format=json")
    fun searchAlbum(
        @Path("api_version") version: String,
        @Query("api_key") apiKey: String,
        @Query("album") searchText: String
    ): Call<AlbumSearchResult>
}