package com.puneet.musicalwipro.albumsearch.model

import android.os.Parcelable
import com.puneet.musicalwipro.albumsearch.extensions.SearchResultType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonSearchResult(
    val resultType: SearchResultType,
    val id: String,
    val title: String,
    val subTitle: String,
    val url: String?,
    val imageUrlMedium: String?,
    val imageUrlLarge: String?
) : Parcelable