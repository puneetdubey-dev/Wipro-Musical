package com.puneet.musicalwipro.albumsearch.model

import com.google.gson.annotations.SerializedName

data class AlbumList(
    @SerializedName("album")
    val albums: List<Album>
)