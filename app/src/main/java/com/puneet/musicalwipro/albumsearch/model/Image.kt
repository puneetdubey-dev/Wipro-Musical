package com.puneet.musicalwipro.albumsearch.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,

    @SerializedName("size")
    val imageSize: ImageSize
)