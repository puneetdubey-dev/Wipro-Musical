package com.puneet.musicalwipro.albumsearch.model

import com.google.gson.annotations.SerializedName

data class AlbumMatches(
    @SerializedName("albummatches")
    val albumList: AlbumList
)