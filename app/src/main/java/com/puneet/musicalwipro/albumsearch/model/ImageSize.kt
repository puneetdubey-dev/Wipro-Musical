package com.puneet.musicalwipro.albumsearch.model

import com.google.gson.annotations.SerializedName

enum class ImageSize {

    @SerializedName("small")
    Small,

    @SerializedName("medium")
    Medium,

    @SerializedName("large")
    Large,

    @SerializedName("extralarge")
    ExtraLarge,

    @SerializedName("mega")
    Mega
}

