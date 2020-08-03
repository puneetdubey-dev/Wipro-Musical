package com.puneet.musicalwipro.albumsearch.model

import com.google.gson.annotations.SerializedName

data class Album(
    val mbid: String,
    val name: String,
    val artist: String,
    val url: String?,

    @SerializedName("image")
    val images: List<Image>?
)